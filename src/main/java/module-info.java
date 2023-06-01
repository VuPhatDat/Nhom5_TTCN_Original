module com.example.nhom16_cnpm {
    requires javafx.controls;
    requires javafx.fxml;


	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;

    
    opens com.example.main to javafx.fxml;
    exports com.example.main;
    
    exports com.example.controller;
    opens com.example.controller to javafx.fxml;

    exports com.example.model;
    opens com.example.model to javafx.fxml;
    
    exports com.example.controller.HoKhauManageController;
    opens com.example.controller.HoKhauManageController to javafx.fxml;
    
    exports com.example.controller.NhanKhauManageController;
    opens com.example.controller.NhanKhauManageController to javafx.fxml;
    exports com.example.controller.PhanAnhManageController;
    opens com.example.controller.PhanAnhManageController to javafx.fxml;
}