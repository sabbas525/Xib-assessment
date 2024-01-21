package com.xib.assessment.constants;

public interface AppConstants {
    final class HTTP_MESSAGES {
        public static final String SUCCESS = "SUCCESS";
        public static final String NOT_FOUND = "Not Found";
    }

    final class VARIABLES {
        public static final String TEAM = "Team";
        public static final String MANAGER = "Manager";
        public static final String AGENT = "Agent";
        public static final String BLANK_SPACE = " ";
        public static final String AGENT_ALREADY_ASSIGNED_MESSAGE = "Agent already assigned to a team";
        public static final String TEAM_MAX_ASSIGNED_MESSAGE = "A team can be managed by at most 2 managers";
    }
    final class EXCEPTION_MESSAGES{
        public static final String TEAM_MAX_MANAGER_EXCEPTION = "A team can have at most 2 managers.";
    }
}
