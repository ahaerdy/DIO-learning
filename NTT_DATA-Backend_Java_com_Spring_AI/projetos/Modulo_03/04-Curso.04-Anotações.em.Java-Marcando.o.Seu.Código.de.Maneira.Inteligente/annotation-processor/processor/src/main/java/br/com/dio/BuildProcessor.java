package br.com.dio;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import com.squareup.javapoet.JavaFile;

import static java.util.stream.Collectors.toMap;
import static javax.lang.model.element.ElementKind.FIELD;

@SupportedAnnotationTypes("vr.com.dio.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BuildProcessor extends AbstractProcessor {
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (var annotation : annotations) {
            for (var element : roundEnv.getElementsAnnotatedWith(annotation)) {
                Map fields = element.getEnclosedElements().stream()
                        .filter(e -> e.getKind() == FIELD)
                        .collect(toMap(e -> e.getSimpleName().toString(), Element::asType));
                var packageName = processingEnv.getElementUtils().getPackageOf(element).toString();
                var className = element.getSimpleName().toString();
                var builderName = className + "Builder";
                var typeSpec = new BuildGenerator().create(packageName, className, builderName, fields);
                var javaFile = JavaFile.builder(packageName, typeSpec).indent("    ").build();

                try {
                    var out = new PrintWriter(processingEnv.getFiler()
                            .createSourceFile(builderName)
                            .openWriter()
                    );
                    out.write(javaFile.toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        return true;
    }

}
