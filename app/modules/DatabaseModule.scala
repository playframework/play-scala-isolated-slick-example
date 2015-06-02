package modules

import javax.inject.{Inject, Provider, Singleton}

import com.google.inject.AbstractModule
import com.typesafe.config.Config
import play.api.Configuration

/**
 *
 */
class DatabaseModule(configuration: Configuration) extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[Config]).toInstance(configuration.underlying)
    bind(classOf[slick.jdbc.JdbcBackend.Database]).toProvider(classOf[DatabaseProvider])
    bind(classOf[slick.UserDAO]).asEagerSingleton()
  }
}

@Singleton
class DatabaseProvider @Inject() (config: Config) extends Provider[slick.jdbc.JdbcBackend.Database] {

  private val db = slick.jdbc.JdbcBackend.Database.forConfig("myapp.database", config)

  // Use Runtime.addShutdownHook()
  //  lifecycle.addStopHook { () =>
  //    Future.successful {
  //      db.close()
  //    }
  //  }

  override def get(): slick.jdbc.JdbcBackend.Database = db
}
