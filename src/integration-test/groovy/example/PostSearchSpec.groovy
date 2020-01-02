package example

import example.all.Post
import grails.gorm.transactions.Rollback
import grails.plugins.elasticsearch.ElasticSearchAdminService
import grails.plugins.elasticsearch.ElasticSearchResult
import grails.plugins.elasticsearch.ElasticSearchService
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class PostSearchSpec extends Specification {

    @Autowired
    ElasticSearchAdminService elasticSearchAdminService
    @Autowired
    ElasticSearchService elasticSearchService

    void "test save and search post"() {

        setup:
        Post.saveAll(
            new Post(subject: "Elasticsearch Demo", body: "The Post for Elasticsearch Demo")
        )

        when:
        elasticSearchService.index(Post)
        elasticSearchAdminService.refresh(Post)
        ElasticSearchResult elasticSearchResult = elasticSearchService.search("Elasticsearch Demo", [indices: Post, type: Post])

        then:
        elasticSearchResult.total == 1

        cleanup:
        elasticSearchAdminService.deleteIndex(Post)
        Post.deleteAll()

    }
}
