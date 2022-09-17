package ers.common;


import ers.users.UserResponse;

public class SecurityUtils {



        public static boolean isDirector(UserResponse subject) {
            return subject.getRole().equals("ADMIN");
        }

    public static boolean isFinanceMan(UserResponse subject) {
        return subject.getRole().equals("FINANCE MANAGER");
    }

        // Only to be used with GET user requests
        public static boolean requesterOwned(UserResponse subject, String resourceId) {
            return subject.getId().equals(resourceId);






        }
    }

