package com.du.common.elasticsearch;

import io.searchbox.core.SearchResult;
import io.searchbox.core.search.sort.Sort;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;

import java.io.IOException;
import java.util.Collection;

/**
 * @author dxy
 * @date 2019/8/26 14:41
 * @date 2019/8/26 14:41
 */
public interface ElasticsearchService {
	/**
	 * 查询(该方法只满足从具体索引，索引类型查询及排序的需求)
	 *
	 * @param indexName          索引名称
	 * @param indexType          索引类型
	 * @param sort               Sort
	 * @param aggregationBuilder AggregationBuilder
	 * @return SearchResult
	 * @throws IOException
	 */
	SearchResult doSearch(String indexName, String indexType, Sort sort, QueryBuilder queryBuilder, AggregationBuilder aggregationBuilder) throws IOException;

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
	SearchResult doSearch(Collection<? extends String> indexNames, String indexType, Collection<Sort> sorts, QueryBuilder queryBuilder, AggregationBuilder aggregationBuilder) throws IOException;

}
