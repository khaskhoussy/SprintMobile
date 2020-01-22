/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Smida *********************PRO******************************
 */
public class ApiStat extends Form {

    /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(50);
        renderer.setLegendTextSize(50);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {

        CategorySeries series = new CategorySeries(title);
        //ServiceRandonnee sv = new ServiceRandonnee();

        // for (double value : values) {
        //            series.add("Offre " + ++k, value);
        //        }
        
        
        Label l1 = new Label();
        ConnectionRequest con1 = new ConnectionRequest();
        con1.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/StatPlante");
        con1.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                String value1 = new String(con1.getResponseData());
                System.out.println(value1);
                System.out.println("hhhhhhhhhhhhhh");
                l1.setText(value1);
                System.out.println(l1.getText());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con1);
        System.out.println("aaaaaaa "+l1.getText());
                double v1 = (double) Float.parseFloat(l1.getText());
        Label l2 = new Label();

        ConnectionRequest con2 = new ConnectionRequest();
        con2.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/StatAccesoire");
        con2.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                String value2 = new String(con2.getResponseData());
                System.out.println(value2);
                      l2.setText(value2);


            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con2);
                double v2 = (double) Double.parseDouble(l2.getText());

                
            Label l3 = new Label();
        ConnectionRequest con3 = new ConnectionRequest();
        con3.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/StatMateriel");
        con3.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {

                String value3 = new String(con3.getResponseData());
                System.out.println(value3);
                l3.setText(value3);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con3);
                double v3 = (double) Double.parseDouble(l3.getText());
           
                
       

        series.add("Plante", v1);
        series.add("Accesoire ", v2);
        series.add("Materiel ", v3);
      
        return series;

    }

    public Form createPieChartForm(Resources theme) {

        // Generate the values
        double[] values = new double[]{50, 99, 11, 30, 25, 60};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.BLACK};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(50);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);
      getToolbar().addCommandToLeftBar("back",null,new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              HomeForm produit=new HomeForm(theme);
                produit.show();
            }
        });
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Pourcentages", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Statistique");
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);

        // menu  cc= new menu(theme);
        //Toolbar
        Toolbar.setGlobalToolbar(true);

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_HOME, s);

//    
//        f.getToolbar().addCommandToLeftBar("Home", icon, (e) -> {
//            
//            
//            
//            Log.p("Clicked");
//              cc.getF().show();
//  
//        
//                
//                
//                });
        return f;
    }

}
