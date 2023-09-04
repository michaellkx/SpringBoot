package main.order.annotation.limit;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes(value = {"main.order.annotation.limit.LengthLimit"})  //此处填写被处理的注解的包路径名
public class LengthLimitProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean b = roundEnv.errorRaised();

        System.out.println("@LengthLimit预处理器执行了");


//        for (TypeElement annotatedClass : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(CheckGetter.class))) {
//            for (VariableElement field : ElementFilter.fieldsIn(annotatedClass.getEnclosedElements())) {
//                if(!containsGetter(annotatedClass, field.getSimpleName().toString())){
//                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
//
//                            String.format("getter not found for '%s.%s'.", annotatedClass.getSimpleName(), field.getSimpleName()));
//
//                }
//
//            }
//
//        }

        return false;
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // 通过init()方法，完成初始化工作
        System.out.println("init方法");
    }


}
