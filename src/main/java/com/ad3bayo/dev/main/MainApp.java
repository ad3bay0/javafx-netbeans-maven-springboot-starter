package com.ad3bayo.dev.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.ad3bayo.dev")
public class MainApp extends Application {
    
    private ConfigurableApplicationContext springContext;
    private Parent parentNode;
    
    /**
     * initialise spring boot beans and load java fx parent node
     * @throws Exception 
     */
     @Override
    public void init() throws Exception {
        
        springContext =  SpringApplication.run(MainApp.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        parentNode = fxmlLoader.load();
       
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
   

    @Override
    public void start(Stage stage) throws Exception {
      
        Scene scene = new Scene(parentNode);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX, Maven and SpringBoot");
        stage.setScene(scene);
        stage.show();
    }

      @Override
    public void stop() throws Exception {
       springContext.stop();
    }
    

}
