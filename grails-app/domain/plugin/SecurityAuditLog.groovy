package plugin

class SecurityAuditLog {
    Date date
    String username
    String fromUser
    String eventDescription
    String ipAddress
    String eventType
    String exceptionType

    static constraints = {
        eventDescription nullable: true
        exceptionType nullable: true
        fromUser nullable: true
    }
}
