package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.ProjectModelManager;
import model.*;
import utils.MyFileHandler;
import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * This class represents the controller handling the addition of Industrial Projects within the application.
 * This controller manages the user interface components and interacts with the project model manager
 * to handle the creation and addition of Industrial Projects.
 *
 * @author Group 1
 */
public class AddIndustrialViewController
{
  public AnchorPane AddIndustrialProjectView;
  private Scene scene;
  private ProjectModelManager projectManager;
  private ViewHandler viewHandler;

  @FXML private TextField projectTypeField;
  @FXML private TextField budgetField;
  @FXML private TextField projectNameField;
  @FXML private TextField dayField;
  @FXML private TextField monthField;
  @FXML private TextField yearField;
  @FXML private ComboBox statusComboBox;
  @FXML private TextField timelineField;
  @FXML private TextField firstNameField;
  @FXML private TextField surnameField;
  @FXML private TextField customerIDField;
  @FXML private TextField manHoursUsedField;
  @FXML private TextField expectedManHoursField;
  @FXML private TextField materialExpensesField;
  @FXML private TextField sizeField;
  @FXML private TextField typeOfTheFacilityField;
  @FXML private Button clearButton;
  @FXML private Button addProjectButton;
  @FXML private Button backButton;

  /**
   * Initializes the view with the provided view handler, scene, and project manager. Also sets up initial values for the status combo box.
   *
   * @param viewHandler     The view handler managing views.
   * @param scene           The scene to be initialized.
   * @param projectManager  The project manager for handling projects.
   */
  public void init(ViewHandler viewHandler, Scene scene, ProjectModelManager projectManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.projectManager = projectManager;
    String[] statusString = {"Under Construction","Finished","Planned"};
    statusComboBox.getItems().addAll(statusString);
    statusComboBox.getSelectionModel().selectFirst();
  }

  /**
   * Checks whether the provided string contains valid alphabetic characters and spaces.
   *
   * @param string The string to be validated.
   * @return {@code true} if the string contains valid characters, otherwise {@code false}.
   */
  public boolean isValidString(String string)
  {
    return string.matches("[a-zA-Z ]+");
  }

  /**
   * Resets all input fields and selections in the form to their default values or empty strings.
   */
  public void reset()
  {
    budgetField.setText("");
    dayField.setText("");
    monthField.setText("");
    yearField.setText("");
    statusComboBox.getSelectionModel().selectFirst();
    timelineField.setText("30");
    firstNameField.setText("");
    surnameField.setText("");
    customerIDField.setText("");
    expectedManHoursField.setText("");
    materialExpensesField.setText("");
    sizeField.setText("");
    manHoursUsedField.setText("");
    typeOfTheFacilityField.setText("");
    projectNameField.setText("");
  }

  /**
   * Retrieves the scene associated with this handler.
   *
   * @return The Scene object associated with this handler.
   */
  public Scene getScene()
  {
    return scene;
  }

  /**
   * Handles various actions triggered by UI components, such as adding projects or clearing input fields.
   *
   * @param e The ActionEvent representing the action triggered.
   * @throws FileNotFoundException If a file required for project handling is not found.
   */
  public void handleActions(ActionEvent e) throws FileNotFoundException
  {
    if(e.getSource()==backButton)
    {
      viewHandler.openView("AddProjectView");
    }
    else if (e.getSource() == clearButton) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation");
      alert.setHeaderText("");
      alert.setContentText("Are you sure you want to clear? This action cannot be undone.");

      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent() && result.get() == ButtonType.OK) {
        reset();
      } else {
        return;
      }
    }
    else if(e.getSource() == addProjectButton)
    {
      ProjectModelManager manager = new ProjectModelManager("projects.bin");

      int budget = 0;
      String projectName;
      int day = 0;
      int month = 0;
      int year = 0;
      String status = null;
      int projectID = projectManager.generateProjectID();
      int timeline = 0;
      String firstName = null;
      String surname = null;
      int customerID = 0;
      int expectedManHours = 0;
      int materialExpenses = 0;
      int manHoursUsed = 0;
      int size = 0;
      String typeOfFacility = null;

      String type = projectTypeField.getText();
      try
      {
        budget = Integer.parseInt(budgetField.getText());
        if (budget < 2000000 || budget > 10000000)
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Error");
          alert.setHeaderText("");
          alert.setContentText("Budget must be between 2,000,000 and 10,000,000.\nDo you want to continue with the entered budget?");

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.CANCEL)
          {
            return;
          }
        }
      }
      catch(NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid budget input");
        alert.showAndWait();
        return;
      }
      if (isValidString(projectNameField.getText()))
      {
        projectName = projectNameField.getText();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid name input");
        alert.showAndWait();
        return;
      }
      try
      {
        day = Integer.parseInt(dayField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid day input");
        alert.showAndWait();
        return;
      }
      try
      {
        month = Integer.parseInt(monthField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid month input");
        alert.showAndWait();
        return;
      }
      try
      {
        year = Integer.parseInt(yearField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid year input");
        alert.showAndWait();
        return;
      }
      status = (String) statusComboBox.getValue();
      try
      {
        timeline = Integer.parseInt(timelineField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid timeline input");
        alert.showAndWait();
        return;
      }
      if (isValidString(firstNameField.getText()))
      {
        firstName = firstNameField.getText();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid customer first name input");
        alert.showAndWait();
        return;
      }
      if (isValidString(surnameField.getText()))
      {
        surname = surnameField.getText();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid customer surname input");
        alert.showAndWait();
        return;
      }
      try
      {
        customerID = Integer.parseInt(customerIDField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid customer ID input");
        alert.showAndWait();
        return;
      }
      try
      {
        expectedManHours = Integer.parseInt(expectedManHoursField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid expected man hours input");
        alert.showAndWait();
        return;
      }
      try
      {
        materialExpenses = Integer.parseInt(materialExpensesField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid material expenses input");
        alert.showAndWait();
        return;
      }
      try
      {
        manHoursUsed = Integer.parseInt(manHoursUsedField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid man hours used input");
        alert.showAndWait();
        return;
      }
      try
      {
        size = Integer.parseInt(sizeField.getText());
      }
      catch (NumberFormatException exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid size input");
        alert.showAndWait();
        return;
      }
      if (isValidString(typeOfTheFacilityField.getText()))
      {
        typeOfFacility = typeOfTheFacilityField.getText();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Invalid facility type input");
        alert.showAndWait();
        return;
      }

      MyDate date = new MyDate(day, month, year);
      if (!date.isValidDate())
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid date input");
        alert.show();
        return;
      }

      Customer customer = new Customer(firstName, surname, customerID);
      Resources resources = new Resources(expectedManHours, materialExpenses,manHoursUsed);
      manager.appendProject(new IndustrialProjects(projectName,budget, date, status, projectID, timeline,
          customer, resources, size, typeOfFacility));
      viewHandler.openView("ProjectView");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setHeaderText(null);
      alert.setContentText("Project successfully added!");
      alert.showAndWait();
      reset();
    }
  }
}
