package ar.edu.unlp.info.bd2.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.bd2.model.Purchase;
import ar.edu.unlp.info.bd2.model.User;

@Repository
public class CustomPurchaseRepositoryImpl implements CustomPurchaseRepository{
	
	@Inject
	private ElasticsearchOperations template;
	
	public List<User> getUsersSpendingMoreThanInPurchase(Float amount){
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		QueryBuilder purchasesSpendingMore = QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("amount").gte(amount));
		queryBuilder.withQuery(purchasesSpendingMore);
		NativeSearchQuery query = queryBuilder.build();
		SearchHits<Purchase> purchasesHits = this.template.search(query, Purchase.class);
		List<User> listUsers = purchasesHits.getSearchHits().stream().map(hit -> hit.getContent().getClient()).collect(Collectors.toList());
		return listUsers;
	}
	
	public List<Purchase> getAllPurchasesMadeByUser(String username){
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		QueryBuilder purchasesOfUser = QueryBuilders.matchQuery("client.email", username).minimumShouldMatch("100%"); 
		queryBuilder.withQuery(purchasesOfUser);
		NativeSearchQuery query = queryBuilder.build();
		SearchHits<Purchase> searchHits = this.template.search(query, Purchase.class);
		List<Purchase> listUsers = searchHits.getSearchHits().stream().map(hit -> hit.getContent()).collect(Collectors.toList());
		return listUsers;
	}

}
