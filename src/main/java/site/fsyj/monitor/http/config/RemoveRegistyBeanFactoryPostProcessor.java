package site.fsyj.monitor.http.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class RemoveRegistyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    //移出bean。 aa是bean的名称
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        if (beanDefinitionRegistry.containsBeanDefinition("aa")) {
            beanDefinitionRegistry.removeBeanDefinition("aa");
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
