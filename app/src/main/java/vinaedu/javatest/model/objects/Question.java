package vinaedu.javatest.model.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by bscenter on 12/08/2016
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Question {
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String trueAnswer;
}
