package plugin.securityauditlog

import org.apache.log4j.Logger
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutHandler
import plugin.SecurityAuditLog

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LogOutHandler implements LogoutHandler {

    static final Logger log = Logger.getLogger(LogOutHandler)

    @Override
    void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        SecurityAuditLog securityAuditLog = new SecurityAuditLog()
        securityAuditLog.date = new Date()
        securityAuditLog.eventType = 'Log Out'
        securityAuditLog.eventDescription = 'Log Out'
        securityAuditLog.username = authentication?.principal?.username
        securityAuditLog.ipAddress = IpUtil.getClientIpAddr(WebUtils.retrieveGrailsWebRequest().request)

        SecurityAuditLog.withNewSession {
            if (!securityAuditLog.save(flush: true)) {
                println("Failed to save the logout Action ${securityAuditLog?.errors}")
                log.info("Failed to save the logout Action ${securityAuditLog?.errors}")
            } else {
                println("logout action registered successfully..${securityAuditLog?.eventType}")
                log.trace("logout action registered successfully..${securityAuditLog?.eventType}")
            }
        }
    }
}

