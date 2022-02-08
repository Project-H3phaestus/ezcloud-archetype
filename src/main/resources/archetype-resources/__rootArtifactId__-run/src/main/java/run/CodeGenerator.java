#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.run;

import io.github.projecthephaestus.ezcloud.mp.generator.EzCloudGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

/**
 * 代码生成器
 *
 * @author ${author}
 */
public final class CodeGenerator {

    public static void main(String[] args) {
        EzCloudGenerator.GeneratorProperty property = new EzCloudGenerator.GeneratorProperty()
            .setDataSourceBuilder(new DataSourceConfig.Builder(
                "database url",
                "username", "passwd"
            ))
            .setProjectName("${rootArtifactId}")
            .setPackagePrefix("${package}".substring(0, "${package}".lastIndexOf(".")))
            .setOverwriteFiles(false);
        EzCloudGenerator.generate(property);
    }
}
