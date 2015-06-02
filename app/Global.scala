import akka.actor.ActorSystem
import com.google.inject.{Injector, Guice, AbstractModule}
import modules.DatabaseModule
import play.api.libs.concurrent.Akka
import play.api.{Play, GlobalSettings}

/**
 * Set up the Guice injector and provide the mechanism for return objects from the dependency graph.
 */
object Global extends GlobalSettings {

  /**
   * Bind types such that whenever TextGenerator is required, an instance of WelcomeTextGenerator will be used.
   */
  var injector: Injector = _

  override def onStart(app : play.api.Application) : scala.Unit = {
    injector = Guice.createInjector(new AbstractModule {
      protected def configure() {
        val configuration = app.configuration
        bind(classOf[ActorSystem]).toInstance(Akka.system(app))
        install(new DatabaseModule(configuration))
      }
    })
  }

  /**
   * Controllers must be resolved through the application context. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = injector.getInstance(controllerClass)
}