package fr.cmm.service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static fr.cmm.SpringProfiles.INTEG;
import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImageServiceTestConfig.class)
@ActiveProfiles(INTEG)
public class RecipeServiceTest {
    @Inject
    private RecipeService recipeService;

    @Inject
    private MongoCollection recipeCollection;

    @Before
    @After
    public void clean() {
        recipeCollection.remove();
    }

    @Test
    void save() {
        recipeService.save(new Recipe(title: 'test recipe'))

        assert recipeCollection.findOne().as(Recipe).title == 'test recipe'
    }

    @Test
    public void findById() {
        Recipe recipe = new Recipe();
        recipe.setTitle("test recipe");

        recipeService.save(recipe);

        assertEquals("test recipe", recipeService.findById(recipe.getId()).getTitle());
    }

    @Test
    public void findByQuery() {
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());

        assert recipeService.findByQuery(new PageQuery()).size() == 5
    }

    @Test
    public void 'findByQuery -> with custom PageSize'() {
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());

        PageQuery pageQuery = new PageQuery();
        pageQuery.setSize(2);

        assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }

    @Test
    public void findByQueryWithTag() {
        5.times {recipeService.save(new Recipe().withTags("tag1"))};

        PageQuery pageQuery = new PageQuery();
        pageQuery.setTag("tag1");

        assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }

    @Test
    public void findAllTags() {
        recipeService.save(new Recipe(tags: ['tag1', 'tag2']));
        recipeService.save(new Recipe().withTags("tag2", "tag3"));

        assertEquals(asList("tag1", "tag2", "tag3"), recipeService.findAllTags());
    }

    @Test
    public void findByIdWithInvalidId() {
        String id = "bad";
        Recipe recette = recipeService.findById(id);
        assertEquals(null, recette);
    }

    @Test
    public void countByQueryUseQuery() {
        PageQuery query = new PageQuery();
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        long nombreRecettes = recipeService.countByQuery(query);
        assertEquals(3, nombreRecettes);
    }
}