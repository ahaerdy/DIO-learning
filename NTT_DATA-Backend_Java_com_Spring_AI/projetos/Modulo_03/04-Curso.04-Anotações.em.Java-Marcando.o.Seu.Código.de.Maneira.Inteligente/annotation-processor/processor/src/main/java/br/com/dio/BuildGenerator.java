package br.com.dio;

import com.squareup.javapoet.*;

import javax.lang.model.type.TypeMirror;
import java.util.Map;

import static javax.lang.model.element.Modifier.*;

public class BuildGenerator {

    public TypeSpec create(
            final String packageName,
            final String className,
            final String builderName,
            final Map<String, TypeMirror> fields
    ) {
        var generaterBuilderClass = TypeSpec.classBuilder(builderName).addModifiers(PUBLIC);

       fields.forEach((k, v) ->
                generaterBuilderClass.addMethod(genBuilderSetter(
                        packageName,
                        builderName,
                        k,
                        TypeName.get(v)
                ))
        );

        var buildMethod = MethodSpec.methodBuilder("build")
                .addModifiers(PUBLIC)
                .returns(ClassName.get(packageName, className))
                .addStatement("var target = new $N()", className);

        fields.keySet().forEach(f -> buildMethod.addStatement(
                "target.set$N($N)",
                f.substring(0, 1).toUpperCase() + f.substring(1),
                f
        ));

        return generaterBuilderClass.addMethod(buildMethod.build()).build();

    }

    private MethodSpec genBuilderSetter(
            final String packageName,
            final String name,
            final String param,
            final TypeName type
    ) {
        return MethodSpec.methodBuilder(param)
                .addModifiers(PUBLIC)
                .returns(ClassName.get(packageName, name))
                .addParameter(type, param, FINAL)
                .addStatement("this.$N = $N", param, param)
                .addStatement("return this")
                .build();
    }

}
