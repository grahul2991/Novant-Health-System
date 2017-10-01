
package com.chandu.phrmesctics;

import com.chandu.utilities.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Shara
 */
public class Productdaos {

       public void create(Product bean)
       {
   ConnectionPool c=ConnectionPool.getInstance();
       c.initialize();
       Connection con=c.getConnection();
       try{
           String sql="insert into product(pname,p_price,stockinhand,dangerlevel)values(?,?,?,?)";

           PreparedStatement pstmt=con.prepareStatement(sql);
           pstmt.setString(1,bean.getPname());
           pstmt.setFloat(2,bean.getP_price());
           pstmt.setInt(3,bean.getStockinhand());
           pstmt.setInt(4,bean.getDangerlevel());


           pstmt.executeUpdate();
            }
       catch(Exception e){
          System.out.println("i am in Create");
       }
       finally{
           c.putConnection(con);
       }
    }

   public void edit(Product bean){
        ConnectionPool c=ConnectionPool.getInstance();
        c.initialize();
        Connection con=c.getConnection();
        try{
            String sql="update product set pname=?,P_price=?,stockinhand=?,dangerlevel=? where pid=?";
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,bean.getPname());
           pstmt.setFloat(2,bean.getP_price());
           pstmt.setInt(3,bean.getStockinhand());
           pstmt.setInt(4,bean.getDangerlevel());
           pstmt.setInt(5, bean.getPid());
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Exception in Edit coomand"+e);
        }
        finally{
            c.putConnection(con);
        }

    }


    public void remove(int id){
        ConnectionPool c=ConnectionPool.getInstance();
        c.initialize();
        Connection con=c.getConnection();
        try{
            String sql="delete from product where pid=?";
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Exception in Edit coomand"+e);
        }
        finally{
            c.putConnection(con);
        }

    }

    public Product find(int id){
         Product ub=new Product();
        ConnectionPool c=ConnectionPool.getInstance();
        c.initialize();
        Connection con=c.getConnection();
        try{
            String sql="select * from product where pid=?";
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){

               ub.setPname(rs.getString("pname"));
               ub.setP_price(rs.getFloat("p_price"));
               ub.setStockinhand(rs.getInt("stockinhand"));
               ub.setDangerlevel(rs.getInt("dangerlevel"));

               ub.setPid(rs.getInt("pid"));

   }


        }
        catch(Exception e){
            System.out.println("Exception in Edit coomand"+e);
        }
        finally{
            c.putConnection(con);
    }
        return ub;
    }


    public ArrayList<Product> findAll(){
        ArrayList<Product> al=new ArrayList<Product>();
          ConnectionPool c=ConnectionPool.getInstance();
        c.initialize();
        Connection con=c.getConnection();
        try{
            String sql="select * from product";
            PreparedStatement pstmt=con.prepareStatement(sql);

            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Product ub=new Product();
               ub.setPname(rs.getString("pname"));
               ub.setP_price(rs.getFloat("p_price"));
               ub.setStockinhand(rs.getInt("stockinhand"));
               ub.setDangerlevel(rs.getInt("dangerlevel"));
              ub.setPid(rs.getInt("pid"));
               

 al.add(ub);
            }


        }
        catch(Exception e){
            System.out.println("Exception in Edit coomand"+e);
        }
        finally{
            c.putConnection(con);
        }
        return al;
    }



    public static void main(String[] args) {
        Productdaos ud=new Productdaos();

    
        
    }

}
