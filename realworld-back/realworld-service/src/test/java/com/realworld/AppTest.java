package com.realworld;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AppTest {

//	@Autowired
//	private ITagsService tagsService;
//	@Test
//	public void testRedis() {
//		List<TagsVO> tagsVOS = tagsService.listTag();
//
//		log.info("tagsVOS: {}", tagsVOS);
//	}

	@Test
	public void testArticle() {
//		ArticlePageQueryDTO articlePageQueryDTO = new ArticlePageQueryDTO(List.of(), false, null, 0, 2);
//		List<ArticleVO> list = articleService.listArticle(articlePageQueryDTO, null);
//		log.info("list: {}", list);
	}

	@Test
	public void testArticleCreate() {
//		List<String> list = new ArrayList<>(List.of("java", "spring"));
//		ArticleCreateDTO articleCreateDTO = new ArticleCreateDTO("标题2", "描述2", "内容2", list);
//		articleService.saveArticle(articleCreateDTO);
	}

	@Test
	public void testArticleUpdate() {
//		ArticleUpdateDTO articleUpdateDTO = new ArticleUpdateDTO("标题1", "描述1", "内容1");
//		articleService.updateArticleById(articleUpdateDTO, 6);
	}
}
