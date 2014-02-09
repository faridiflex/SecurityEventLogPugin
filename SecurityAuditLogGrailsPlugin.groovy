import plugin.securityauditlog.LogOutHandler
import plugin.securityauditlog.SpringSecurityEventListener

class SecurityAuditLogGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
//            TODO MF: Identify the things we can exlude
    ]

    def loadAfter = ['spring-security-core']
    // TODO Fill in these fields
    def title = "Security Audit Log Plugin" // Headline display name of the plugin
    def author = "Mohd Farid"
    def authorEmail = "farid@intelligrape.com"
    def description = '''\
    If you want your application to log information like who logged in to the application, at what time and from which IP address.
    When did he/she logged out then this plugin is for you.
    It also logs switch user events.

    You just need to install plugin and it will start logging the security events immediately. In order to view the SecurityLogs,
    you need to write a controller/action to render the data from SecurityAuditLog domain.

        You must be using Spring Security Core Plugin in your application in order to use this plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/security-audit-log"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        springSecurityEventListener(SpringSecurityEventListener)
        auditLogOutHandler(LogOutHandler)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        def logOutHandlers = application.config.grails.plugins.springsecurity.logout.handlerNames?:[]
        logOutHandlers  << 'auditLogOutHandler'
        logOutHandlers.unique()
        application.config.grails.plugins.springsecurity.logout.handlerNames = logOutHandlers

    }

    def onChange = { event ->
//        TODO MF: identify the tasks to be done on change
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
