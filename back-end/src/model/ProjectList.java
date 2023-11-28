package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The ProjectList class represents a collection of projects.
 * It provides methods to add and remove projects, as well as retrieve projects based on status or type.
 * The class also has a method to generate a string representation of all projects in the list.
 *
 * @author Samuel Kacenga / Romans Mihalonoks
 */
public class ProjectList implements Serializable
{

  /**
   * The list that holds all the projects.
   */
  private ArrayList<Project> projects;

  /**
   * Constructs a new ProjectList with an empty list of projects.
   */
  public ProjectList(){
    projects = new ArrayList<Project>();
  }

  /**
   * Adds a project to the list.
   *
   * @param project The project to be added.
   */
  public void addProject(Project project){
    projects.add(project);
  }

  /**
   * Removes a project from the list.
   *
   * @param project The project to be removed.
   */
  public void removeProject(Project project){
    projects.remove(project);
  }

  /**
   * Retrieves all projects with a specified status.
   *
   * @param status The status to filter projects by.
   * @return An ArrayList containing indices of projects with the specified status.
   */
  public ArrayList<Object> getAllByStatus(String status)
  {
    ArrayList<Object> statusList = new ArrayList<>();

    for (int i = 0; i < projects.size(); i++)
    {
      if (projects.get(i).getStatus().equals(status)){
        statusList.add(i);
      }
    }
    return statusList;
  }

  /**
   * Retrieves all projects with a specified type.
   *
   * @param type The type to filter projects by.
   * @return An ArrayList containing indices of projects with the specified type.
   */
  public ArrayList<Object> getAllByType(String type)
  {
    ArrayList<Object> typeList = new ArrayList<>();

    for (int i = 0; i < projects.size(); i++)
    {
      if (projects.get(i).getType().equals(type))
      {
        typeList.add(i);
      }
    }
    return typeList;
  }

  /**
   * Generates a string representation of all projects in the list.
   *
   * @return A string containing information about all projects in the list.
   */
  public String toString()
  {
    StringBuilder returnStr = new StringBuilder();

    for(int i = 0; i<projects.size(); i++)
    {
      Project temp = projects.get(i);

      returnStr.append(temp).append("\n");
    }
    return returnStr.toString();
  }
}
