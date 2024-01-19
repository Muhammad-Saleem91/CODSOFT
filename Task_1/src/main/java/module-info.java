module com.example.task_1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.task_1 to javafx.fxml;
    exports com.example.task_1;
}