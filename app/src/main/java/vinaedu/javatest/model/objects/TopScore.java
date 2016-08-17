package vinaedu.javatest.model.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by bscenter on 12/08/2016
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class TopScore {
    private int score;
    private int level;
}
