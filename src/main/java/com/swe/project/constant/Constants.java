package com.swe.project.constant;

public final class Constants {

    private Constants() {}

    // User messages
    public static final String USER_NOT_FOUND = "Người dùng với ID %d không tồn tại";
    public static final String HOTEL_NOT_FOUND = "Khách sạn với ID %d không tồn tại";

    // Password / auth
    public static final String CURRENT_PASSWORD_INCORRECT = "Mật khẩu hiện tại không chính xác";
    public static final String NEW_PASSWORD_MUST_DIFFER = "Mật khẩu mới phải khác mật khẩu hiện tại";
    public static final String PASSWORD_CHANGE_INITIATED = "Yêu cầu thay đổi mật khẩu đã được khởi tạo. Vui lòng xác thực bằng mã đã gửi tới email của bạn.";

    // Verification codes
    public static final String VERIFICATION_CODE_EXPIRED = "Mã xác thực %s đã hết hạn.";
    public static final String VERIFICATION_CODE_NOT_FOUND = "Không tìm thấy mã xác thực: %s";
    public static final String VERIFICATION_CODE_ALREADY_USED = "Mã xác thực %s đã được sử dụng.";

    // Registration / login
    public static final String USER_ALREADY_EXISTS = "Người dùng với email %s đã tồn tại.";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Email hoặc mật khẩu không hợp lệ";
    public static final String UNAUTHORIZED = "Bạn chưa đăng nhập";
    public static final String FORBIDDEN = "Bạn không có quyền thực hiện hành động này";

    // JWT messages
    public static final String JWT_TOKEN_EXPIRED = "Token hết hạn";
    public static final String JWT_TOKEN_INVALID = "Token không hợp lệ!";
    public static final String USER_LOGGED_OUT = "Người dùng đã đăng xuất";

    // General
    public static final String LOGOUT_SUCCESS = "Người dùng với ID %d đã đăng xuất thành công.";

    // Hotel Room messages
    public static final String ROOM_NOT_FOUND = "Phòng số %s không tồn tại cho khách sạn ID %s";
    public static final String ROOM_NUMBER_ALREADY_EXISTS = "Phòng số %s đã tồn tại cho khách sạn này";
    public static final String ROOM_CREATED_SUCCESS = "Phòng số %s đã được tạo thành công";
    public static final String ROOM_UPDATED_SUCCESS = "Phòng số %s đã được cập nhật thành công";
    public static final String ROOM_DELETED_SUCCESS = "Phòng số %s đã được xóa thành công";
    public static final String PERMISSION_DENIED_ROOM = "Bạn không có quyền để thực hiện hành động này với phòng";
    public static final String INVALID_ROOM_STATUS = "Trạng thái không hợp lệ: %s. Các trạng thái hợp lệ là: ACTIVE, INACTIVE, MAINTENANCE";
    public static final String ROOM_NOT_AVAILABLE = "Phòng số %s không có sẵn cho ngày đã chọn tại khách sạn ID %s";

    // Booking messages
    public static final String BOOKING_NOT_FOUND = "Lịch đặt phòng với ID %d không tồn tại";
    public static final String NO_COMPLETED_BOOKING = "Người dùng với ID %d không có lịch đặt phòng hoàn tất cho khách sạn với ID %d";

    // Review messages
    public static final String REVIEW_NOT_FOUND = "Đánh giá với ID %d không tồn tại";
    public static final String DUPLICATE_REVIEW = "Người dùng với ID %d đã gửi đánh giá cho khách sạn với ID %d rồi";
}

