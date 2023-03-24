package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger("log");
    Map<String, Class> beanMap = new HashMap<>();
    Map<String, String> levelMap = new HashMap<>();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getAnnotation(Inspect.class) != null) {
            levelMap.put(beanName, bean.getClass().getAnnotation(Inspect.class).level());
            beanMap.put(beanName, bean.getClass());
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanMap.containsKey(beanName)) {
            return Proxy.newProxyInstance(
                    CalculatorImpl.class.getClassLoader(),
                    CalculatorImpl.class.getInterfaces(),
                    (proxy, method, args) -> {
                        if (levelMap.get(beanName).equals("debug")){
                            LOGGER.debug("Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args));
                        }
                        else if (levelMap.get(beanName).equals("info")){
                            LOGGER.info("Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args));
                        }
                        return method.invoke(bean, args);
                    }
            );
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
// END
