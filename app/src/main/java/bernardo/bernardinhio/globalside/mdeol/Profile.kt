package bernardo.bernardinhio.globalside.mdeol

class Profile(
    var listTimelineEvents: List<TimelineEvent> = mutableListOf(),
    var listHealthPrompts: List<HealthPrompt> = mutableListOf(),
    var displayName: String = "",
    var gender: String = "",
    var isPrimary: Boolean = false,
    var tariffLabel: String = "",
    var tariffIconUrl: String = "",
    var tariffIconPrimaryColor: String = "",
    var tariffIconSecondaryColor: String = "",
    var dateOfBirth: String = "",
    var address: String = "",
    var email: String = "",
    var phone: String = ""
)