package gr.senik

//import org.hibernate.dialect.PostgreSQLPGObjectJdbcType
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.aot.hint.TypeReference
import java.lang.module.Configuration
import java.lang.module.ResolvedModule


class MyRuntimeHints : RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        // Temporary hint, should be included into the official spring boot project
        hints.reflection().registerTypeIfPresent(
                classLoader, "org.postgresql.util.PGobject"
        )
        // TODO PostgreSQLPGObjectJdbcType
//        { hint ->
//            hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INTROSPECT_PUBLIC_METHODS)
//                    .onReachableType(PostgreSQLPGObjectJdbcType::class.java)
//        }

        hints.reflection().registerType(Module::class.java, MemberCategory.INVOKE_DECLARED_METHODS)
        hints.reflection().registerType(ModuleLayer::class.java, MemberCategory.INVOKE_DECLARED_METHODS)
        hints.reflection().registerType(Configuration::class.java, MemberCategory.INVOKE_DECLARED_METHODS)
        hints.reflection().registerType(ResolvedModule::class.java, MemberCategory.INVOKE_DECLARED_METHODS)

        listOf(
                "io.hypersistence.utils.hibernate.type.json.JsonType",

                "nonapi.io.github.classgraph.classloaderhandler.AntClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.ClassGraphClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry",
                "nonapi.io.github.classgraph.classloaderhandler.CxfContainerClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.EquinoxClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.EquinoxContextFinderClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.FallbackClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.FelixClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.JBossClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.JPMSClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.OSGiDefaultClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.ParentLastDelegationOrderTestClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.PlexusClassWorldsClassRealmClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.QuarkusClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.SpringBootRestartClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.TomcatWebappClassLoaderBaseHandler",
                "nonapi.io.github.classgraph.classloaderhandler.UnoOneJarClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.URLClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.WeblogicClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.WebsphereLibertyClassLoaderHandler",
                "nonapi.io.github.classgraph.classloaderhandler.WebsphereTraditionalClassLoaderHandler",

                "org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer"
        ).forEach { clazz ->
            hints.reflection().registerType(
                    TypeReference.of(clazz),
                    MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                    MemberCategory.INTROSPECT_PUBLIC_METHODS,
                    MemberCategory.INVOKE_DECLARED_METHODS,
                    MemberCategory.DECLARED_FIELDS,
                    MemberCategory.PUBLIC_FIELDS
            )
        }
    }


}
