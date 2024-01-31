package controlls;

import models.Products;



public interface I_Collection {

    public boolean add();

    public boolean delete();

    public boolean update();

    public Products search(String id); 

    public void showInfor();

    public boolean sort();

}
