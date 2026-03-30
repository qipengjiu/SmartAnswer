

import com.chd.ragSmartAnswer.ragSmartAnswerApp;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ragSmartAnswerApp.class)
public class LLMTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testLLM(){
        String answer = qwenChatModel.chat("你好");
        System.out.println(answer);
    }

    @Value("${DASH_SCOPE_API_KEY}")
    private String apiKey;
    @Test
    public void testEnv(){
        System.out.println("API Key: " + apiKey);
    }

    @Autowired
    private EmbeddingModel embeddingModel;

    @Test
    public void testEmbeddingModel(){
        Response<Embedding> embed = embeddingModel.embed("你好");

        System.out.println("向量维度" + embed.content().vector().length);
        System.out.println("向量输出" + embed.toString());
    }

}
