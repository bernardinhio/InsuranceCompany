package bernardo.bernardinhio.globalside.model

class Profile(
    var profileId: String = "",
    var displayedName: String = "",
    var isPrimary: Boolean = false,
    var gender: String = "",
    var dateOfBirth: String = "",
    var tariffLabel: String = "",
    var tariffIconUrl: String = "",
    var tariffIconPrimaryColor: String = "#FFFFFF",
    var tariffIconSecondaryColor: String = "#FFFFFF",
    var address: String = "",
    var contact: String = "",
    var listTimelineEvents: List<TimelineEvent> = mutableListOf(),
    var listHealthPrompts: List<HealthPrompt> = mutableListOf()
)