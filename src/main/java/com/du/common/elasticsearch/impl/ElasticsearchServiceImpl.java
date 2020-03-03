package com.du.common.elasticsearch.impl;

import com.du.common.elasticsearch.ElasticsearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.sort.Sort;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

/**
 * ElasticsearchServiceImpl
 *
 * @author dxy
 * @date 2019/8/26 14:41
 */
@Service(value = "elasticsearchService")
public class ElasticsearchServiceImpl implements ElasticsearchService {
	@Autowired
	private JestClient jestClient;

	/**
	 * 查询
	 *
	 * @param indexName          索引名称
	 * @param indexType          类型类型
	 * @param sort               Sort
	 * @param queryBuilder       QueryBuilder
	 * @param aggregationBuilder AggregationBuilder
	 * @return SearchResult
	 * @throws IOException
	 */
	@Override
	public SearchResult doSearch(String indexName, String indexType, Sort sort, QueryBuilder queryBuilder, AggregationBuilder aggregationBuilder) throws IOException {
		//创建SearchSourceBuilder
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//添加QueryBuilder
		if (queryBuilder != null) {
			searchSourceBuilder.query(queryBuilder);
		}
		//添加AggregationBuilder
		if (aggregationBuilder != null) {
			searchSourceBuilder.aggregation(aggregationBuilder);
		}
		// 设置默认返回条数10000
		searchSourceBuilder.size(10000);
		//创建Search.Builder
		Search.Builder builder = new Search.Builder(searchSourceBuilder.toString());
		//添加索引名称
		if (StringUtils.isNotBlank(indexName)) {
			builder.addIndex(indexName);
		}
		//添加索引类型
		if (StringUtils.isNotBlank(indexType)) {
			builder.addType(indexType);
		}
		//添加Sort
		if (sort != null) {
			builder.addSort(sort);
		}
		//构建Search
		Search search = builder.build();
		return jestClient.execute(search);
	}

	/**
	 * 查询
	 *
	 * @param indexNames         索引名称集合
	 * @param indexType          索引类型集合
	 * @param sorts              Sort
	 * @param queryBuilder       QueryBuilder
	 * @param aggregationBuilder AggregationBuilder
	 * @return SearchResult
	 * @throws IOException
	 */
	@Override
	public SearchResult doSearch(Collection<? extends String> indexNames, String indexType, Collection<Sort> sorts, QueryBuilder queryBuilder, AggregationBuilder aggregationBuilder) throws IOException {
		//创建SearchSourceBuilder
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//添加QueryBuilder
		if (queryBuilder != null) {
			searchSourceBuilder.query(queryBuilder);
		}
		//添加AggregationBuilder
		if (aggregationBuilder != null) {
			searchSourceBuilder.aggregation(aggregationBuilder);
		}
		//创建Search.Builder
		Search.Builder builder = new Search.Builder(searchSourceBuilder.toString());
		//添加索引名称
		if (CollectionUtils.isNotEmpty(indexNames)) {
			builder.addIndices(indexNames);
		}
		//添加索引类型
		if (StringUtils.isNotBlank(indexType)) {
			builder.addType(indexType);
		}
		//添加Sort
		if (CollectionUtils.isNotEmpty(sorts)) {
			builder.addSort(sorts);
		}
		//构建Search
		Search search = builder.build();
		return jestClient.execute(search);
	}

}
