# Spring Mobile 2.0.0.M3 

* https://spring.io/projects/spring-mobile#learn


```xml
dependencies {
    compile 'org.springframework.mobile:spring-mobile-device:2.0.0.M3'
}repositories {
    maven {
        url 'https://repo.spring.io/libs-milestone'
    }
}
```

```java
@Bean
public DeviceResolverHandlerInterceptor
        deviceResolverHandlerInterceptor() {
    return new DeviceResolverHandlerInterceptor();
}

@Bean
public DeviceHandlerMethodArgumentResolver
        deviceHandlerMethodArgumentResolver() {
    return new DeviceHandlerMethodArgumentResolver();
}

@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(deviceResolverHandlerInterceptor());
}

@Override
public void addArgumentResolvers(
        List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(deviceHandlerMethodArgumentResolver());
}
```


#### Inject the Device into your controller:

```java
@Controller
public class HomeController {

    private static final Logger logger =
            LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public void home(Device device) {
        if (device.isMobile()) {
            logger.info("Hello mobile user!");
        } else if (device.isTablet()) {
            logger.info("Hello tablet user!");
        } else {
            logger.info("Hello desktop user!");
        }
    }

}
```

#### Site preference

```java

@Bean
public DeviceResolverHandlerInterceptor
        deviceResolverHandlerInterceptor() {
    return new DeviceResolverHandlerInterceptor();
}

@Bean
public SitePreferenceHandlerInterceptor
        sitePreferenceHandlerInterceptor() {
    return new SitePreferenceHandlerInterceptor();
}

@Bean
public SitePreferenceHandlerMethodArgumentResolver
        sitePreferenceHandlerMethodArgumentResolver() {
    return new SitePreferenceHandlerMethodArgumentResolver();
}

@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(deviceResolverHandlerInterceptor());
    registry.addInterceptor(sitePreferenceHandlerInterceptor());
}
@Override
public void addArgumentResolvers(
        List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(sitePreferenceHandlerMethodArgumentResolver());
}
```

#### Inject the SitePreference into your controller:

```java
@Controller
public class HomeController {

    private static final Logger logger =
            LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home(SitePreference sitePreference, Model model) {
        if (sitePreference == SitePreference.NORMAL) {
            logger.info("Site preference is normal");
            return "home";
        } else if (sitePreference == SitePreference.MOBILE) {
            logger.info("Site preference is mobile");
            return "home-mobile";
        } else if (sitePreference == SitePreference.TABLET) {
            logger.info("Site preference is tablet");
            return "home-tablet";
        } else {
            logger.info("no site preference");
            return "home";
        }
    }

}
```

#### Device aware view resolution
- /WEB-INF/views/mobile/ and tablet views in /WEB-INF/views/tablet/.

```java
@Bean
public LiteDeviceDelegatingViewResolver liteDeviceAwareViewResolver() {
    InternalResourceViewResolver delegate =
            new InternalResourceViewResolver();
    delegate.setPrefix("/WEB-INF/views/");
    delegate.setSuffix(".jsp");
    LiteDeviceDelegatingViewResolver resolver =
            new LiteDeviceDelegatingViewResolver(delegate);
    resolver.setMobilePrefix("mobile/");
    resolver.setTabletPrefix("tablet/");
    return resolver;
}
```
