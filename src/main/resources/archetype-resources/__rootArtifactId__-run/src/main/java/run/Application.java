#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.run;

import io.github.projecthephaestus.ezcloud.autoconfigure.annotation.mvc.SpringCloudApplicationAllFeatEnabled;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author ${author}
 */
@SpringCloudApplicationAllFeatEnabled(
    componentBasePackages={"${package}", "io.github.projecthephaestus"},
    entityBasePackages={"${package}.entity"},
    repositoryBasePackages={"${package}"},
    feignBasePackages={"${package}.feign"},
    mybatisBasePackages={"${package}..**.mapper"}
)
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }
}
