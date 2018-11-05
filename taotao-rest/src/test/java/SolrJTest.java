import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 测试solrJ的操作
 * 
 * @author Administrator
 * 
 */
public class SolrJTest {

	// 添加索引
	@Test
	public void addDocument() throws Exception {
		// 创建一连接
		SolrServer solrServer = new HttpSolrServer(
				"http://192.168.253.181:8080/solr");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品2");
		document.addField("item_price", 54321);
		// 把文档对象写入索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	// 删除索引
	@Test
	public void deleteDocument() throws Exception {
		// 创建一连接
		SolrServer solrServer = new HttpSolrServer(
				"http://192.168.253.181:8080/solr");
		// solrServer.deleteById("test001");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}

	// 查询索引
	@Test
	public void queryDocument() throws Exception {
		SolrServer solrServer = new HttpSolrServer(
				"http://192.168.253.181:8080/solr");
		// 创建一个查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(50);
		// 执行查询
		QueryResponse response = solrServer.query(query);
		// 取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("共查询到记录：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));

		}
	}

	/**
	 * 集群版测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddDocument() throws Exception {
		// 创建一个和solr集群的连接
		// 参数就是zookeeper的地址列表，使用逗号分隔
		String zkHost = "192.168.253.181:2181,192.168.253.181:2182,192.168.253.181:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		// 设置默认的collection
		solrServer.setDefaultCollection("collection2");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		// 向文档中添加域
		document.addField("id", "test001");
		document.addField("item_title", "测试商品");
		// 把文档添加到索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	@Test
	public void deleteDocumentCluster() throws SolrServerException, IOException {
		// 创建一个和solr集群的连接
		// 参数就是zookeeper的地址列表，使用逗号分隔
		String zkHost = "192.168.25.154:2181,192.168.25.154:2182,192.168.25.154:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		// 设置默认的collection
		solrServer.setDefaultCollection("collection2");

		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
}