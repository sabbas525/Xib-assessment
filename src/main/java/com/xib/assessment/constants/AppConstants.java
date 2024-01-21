package com.xib.assessment.constants;
/**
 * Constants used throughout the application.
 * This includes HTTP messages, common variables, and exception messages.
 */
public interface AppConstants {
    // HTTP response messages
    final class HTTP_MESSAGES {
        public static final String SUCCESS = "Success";
        public static final String NOT_FOUND = "Not Found";
        public static final String INVALID_PARAMETERS = "Invalid Parameters";
    }
    // Commonly used variable names
    final class VARIABLES {
        public static final String TEAM = "Team";
        public static final String MANAGER = "Manager";
        public static final String AGENT = "Agent";
        public static final String BLANK_SPACE = " ";

    }
    // Custom exception messages used in the application
    final class EXCEPTION_MESSAGES {
        public static final String TEAM_MAX_MANAGER_EXCEPTION = "A team can have at most 2 managers.";
        public static final String AGENT_TEAM_MANAGER_VALIDATION_MESSAGE = "Agent can only be assigned to a team managed by their manager";
        public static final String AGENT_TEAM_MANAGER_VALIDATION_MESSAGE_2 = "Agent's manager is not managing the team";
        public static final String AGENT_ALREADY_ASSIGNED_MESSAGE = "Agent already assigned to a team";
        public static final String TEAM_MAX_ASSIGNED_MESSAGE = "A team can be managed by at most 2 managers";
    }
}
