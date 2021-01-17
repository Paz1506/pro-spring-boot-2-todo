# CUSTOM BANNER LOCATION
VM arg:

-Dspring.banner.location=classpath:/META-INF/banner-zps.txt

Or you can cnange property in the application.properties:
spring.banner.location=classpath:/META-INF/banner-zps.txt

Or you can disable a banner:
spring.main.banner-mode=off

And:
app.setBannerMode(Banner.Mode.OFF);

# Application args:
-Dspring-boot.run.arguments="--enable"