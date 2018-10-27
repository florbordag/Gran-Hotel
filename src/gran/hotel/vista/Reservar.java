/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel.vista;
import gran.hotel.Conexion;
import gran.hotel.HabitacionData;
import gran.hotel.Reserva;
import gran.hotel.ReservaData;
import gran.hotel.TipoDeHabitacionData;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Flor
 */
public class Reservar extends javax.swing.JFrame {

    private Conexion con;
    
    int diassalida = 0;
    int id_hab_elegida = 0;
    int nro_hab_elegida;
    int adultos;
    int niños;
    int total = 0;
    
    int id_reserva;
    
    private Date f_llegada;
    private Date f_salida;
    private double costototal;
    
    private int id = 0;
    private String tipo = null;
    private int capacidad = 0;
    private int cant_camas = 0;
    private String tipo_cama = null;
    private double precio_noche = 0;

    /**
     * Creates new form HotelSafari
     */
    public Reservar() {
              this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setVisible(true);  
        initComponents();
        
btnsiguientehab.setEnabled(false);
Toolkit tk= Toolkit.getDefaultToolkit();
int xsize= (int) tk.getScreenSize().getWidth();
int ysize= (int) tk.getScreenSize().getHeight();
this.setSize(xsize, ysize);
    }
        public void registrarReserva(){
        Reserva r = new Reserva();
        
        java.util.Date utilStartDate = fechallegada.getDate();
        java.sql.Date f_llegada = new java.sql.Date(utilStartDate.getTime());
        java.util.Date utilEndDate = fechasalida.getDate();
        java.sql.Date f_salida = new java.sql.Date(utilEndDate.getTime());
        
        r.setId_habitacion(id_hab_elegida);
        r.setFecha_entrada(f_llegada);
        r.setFecha_salida(f_salida);
        r.setImporte(costototal);
        r.setCantidad_personas(total);
        r.setEstado(2);
        
        try {
            con = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            ReservaData rd = new ReservaData(con);
            
            //En id_reserva, obtengo el ID asignado a esta reserva, esto me permitirá luego guardar el ID del huesped en
            //la misma reserva.
            id_reserva = rd.insertar(r);
            
        } catch (Exception e) {
        }
    }
            public void mostrarTipos(int cantidad) {
        try {
            //labeltipos.setText("TIPOS DE HABITACIONES DISPONIBLES");
            con = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            TipoDeHabitacionData thd = new TipoDeHabitacionData(con);

            DefaultTableModel modelo;
            modelo = thd.buscardisponibles(cantidad);

            //El siguiente if comprueba que la tabla modelo no este vacia. Ésta estará vacia si la cantidad
            //de personas ingresadas supera la capacidad de cualquier tipo de habitación.
            if (modelo.getRowCount() == 0) {
                JOptionPane.showConfirmDialog(null, "La cantidad de personas supera la capacidad de cualquier habitacion. Por favor haga mas de una reserva");
            } else {
                tablatiposhab.setModel(modelo);
            }

        } catch (Exception e) {
            System.out.println("error mostrartipos.java");
        }
    }
          public void mostrarHabitaciones() {
        try {
            costototal = precio_noche * diassalida;
            con = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
            HabitacionData thd = new HabitacionData(con);

            DefaultTableModel modelo;
            modelo = thd.buscardisponibles(id, diassalida,costototal);

            tablahabs.setModel(modelo);

        } catch (Exception e) {
            System.out.println("error reservacliente.java");
        }
    }
              public void limpiar() {
        fechallegada.setCalendar(null);
        fechasalida.setCalendar(null);
        cboxadultos.setSelectedIndex(0);
        cboxniños.setSelectedIndex(0);
    }
              public void comprobarFechaSalida(int año, int mes, int dia) {
        int fsalida[] = {1, 2, 2};
        System.out.println("llego hasta aca tambien");

        if (fechasalida.getDate() != null) {   //Compruebo que se haya ingreado una fecha de llegada
            //En las siguientes 4 lineas obtengo la fecha de llegada en formato String:
            String dias = Integer.toString(fechasalida.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mess = Integer.toString(fechasalida.getCalendar().get(Calendar.MONTH) + 1);
            String years = Integer.toString(fechasalida.getCalendar().get(Calendar.YEAR));
            //String fechas = (dias + "-" + mess + "-" + years);
            //System.out.println(fechal);

            //A continuacion convierto las fechas en enteros para luego compararlas entre ellas y con la fecha actual
            //con el finde comprobr que:
            //  *la fecha de llegada sea igual o mayor a la fecha actual
            //  *la fecha de salida sea mayor a la fecha de llegada           
            fsalida[0] = Integer.parseInt(dias);
            fsalida[1] = Integer.parseInt(mess);
            fsalida[2] = Integer.parseInt(years);

            //A continuacion Compruebo que la fecha de salida ingresada sea mayor a la fecha de llegada
            /*Calendar fecha = Calendar.getInstance();
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int mes = fecha.get(Calendar.MONTH) + 1;
            int año = fecha.get(Calendar.YEAR);*/
            f_salida = new Date(año, mes, dia);
            Date fechaProxima = new Date(fsalida[2], fsalida[1], fsalida[0]);

            diassalida = (int) ((fechaProxima.getTime() - f_salida.getTime()) / 86400000);
            System.out.println("Hay " + diassalida + " dias de diferencia");

            if (diassalida > 0) { //Si a fecha de salida es mayor que a de llegada, entonces el dato esta bien y paso a comprobar la fecha de salida
                comprobarCampos();
            } else {
                JOptionPane.showConfirmDialog(null, "Ingrese una fecha de salida mayor a la fecha de llegada");
            }

        } else {
            JOptionPane.showConfirmDialog(null, "Ingrese una fecha de salida");
        }
    }
    public void comprobarFechaLlegada() {
        int fllegada[] = {1, 2, 2};

        if (fechallegada.getDate() != null) {   //Compruebo que se haya ingreado una fecha de llegada
            //En las siguientes 4 lineas obtengo la fecha de llegada en formato String:
            String dial = Integer.toString(fechallegada.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mesl = Integer.toString(fechallegada.getCalendar().get(Calendar.MONTH) + 1);
            String yearl = Integer.toString(fechallegada.getCalendar().get(Calendar.YEAR));
            //String fechal = (dial + "-" + mesl + "-" + yearl);
            //System.out.println(fechal);

            //A continuacion convierto las fechas en enteros para luego compararlas entre ellas y con la fecha actual
            //con el finde comprobr que:
            //  *la fecha de llegada sea igual o mayor a la fecha actual
            //  *la fecha de salida sea mayor a la fecha de llegada           
            fllegada[0] = Integer.parseInt(dial);
            fllegada[1] = Integer.parseInt(mesl);
            fllegada[2] = Integer.parseInt(yearl);

            //A continuacion Compruebo que la fecha de entrada ingresada sea igual o mayor a la actual
            Calendar fecha = Calendar.getInstance();
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int mes = fecha.get(Calendar.MONTH) + 1;
            int año = fecha.get(Calendar.YEAR);
            f_llegada = new Date(año, mes, dia);
            Date fechaProxima = new Date(fllegada[2], fllegada[1], fllegada[0]);

            int dias = (int) ((fechaProxima.getTime() - f_llegada.getTime()) / 86400000);
            System.out.println("Hay " + dias + " dias de diferencia");

            if (dias >= 0) { //Si a fecha de llegada coincide con la actual o es mayor, entonces el dato esta bien y paso a comprobar la fecha de salida
                System.out.println("llego hasta aca");
                comprobarFechaSalida(fllegada[2], fllegada[1], fllegada[0]);
            } else {
                JOptionPane.showConfirmDialog(null, "Ingrese una fecha de llegada correcta");
            }

        } else {
            JOptionPane.showConfirmDialog(null, "Ingrese una fecha de llegada");
        }
    }

    public void comprobarCampos() {
        if ((cboxadultos.getSelectedItem().toString()).equals("Seleccione")) {        //Compruebo si se ha indicado la cantidad de adultos

            JOptionPane.showConfirmDialog(null, "Ingrese la cantidad de adultos");

        } else if ((cboxniños.getSelectedItem().toString()).equals("Seleccione")) {    //Compruebo si se ha indicado la cantidad de niños

            JOptionPane.showConfirmDialog(null, "Ingrese la cantidad de niños, 0 para ninguno");

        } else {
            adultos = Integer.parseInt(cboxadultos.getSelectedItem().toString());   //OBTENGO LA CANTIDAD DE ADULTOS
            niños = Integer.parseInt(cboxniños.getSelectedItem().toString());       //OBTENGO LA CANTIDAD DE NIÑOS
            total = adultos + niños;    //SUMO LA CANTIDAD DE ADULTOS Y NIÑOS
            mostrarTipos(total);           
        }
    }
              
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        fechavalida = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelTres = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboxadultos = new javax.swing.JComboBox<>();
        cboxniños = new javax.swing.JComboBox<>();
        btnsiguiente = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnvolver = new javax.swing.JButton();
        fechallegada = new com.toedter.calendar.JDateChooser();
        fechasalida = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablatiposhab = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablahabs = new javax.swing.JTable();
        btnsiguientehab = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        Admin = new javax.swing.JButton();
        reserve = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingrese una fecha válida.");

        jButton1.setText("Aceptar");

        javax.swing.GroupLayout fechavalidaLayout = new javax.swing.GroupLayout(fechavalida);
        fechavalida.setLayout(fechavalidaLayout);
        fechavalidaLayout.setHorizontalGroup(
            fechavalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fechavalidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(fechavalidaLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jButton1)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        fechavalidaLayout.setVerticalGroup(
            fechavalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fechavalidaLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fechavalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fechavalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelTres.setBackground(new java.awt.Color(0, 0, 0));
        panelTres.setToolTipText("");
        panelTres.setAlignmentX(0.0F);
        panelTres.setAlignmentY(0.0F);
        panelTres.setMinimumSize(new java.awt.Dimension(0, 0));
        panelTres.setPreferredSize(new java.awt.Dimension(0, 0));
        panelTres.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                panelTresPropertyChange(evt);
            }
        });
        panelTres.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Reserva - Ingrese los datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(204, 204, 0));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha de llegada:");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha de salida:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cantidad de aultos:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Cantidad de niños");

        cboxadultos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        cboxniños.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        btnsiguiente.setForeground(new java.awt.Color(204, 204, 0));
        btnsiguiente.setText("SIGUIENTE");
        btnsiguiente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 2));
        btnsiguiente.setContentAreaFilled(false);
        btnsiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsiguienteActionPerformed(evt);
            }
        });

        btnlimpiar.setForeground(new java.awt.Color(204, 204, 0));
        btnlimpiar.setText("LIMPIAR");
        btnlimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 2));
        btnlimpiar.setContentAreaFilled(false);
        btnlimpiar.setPreferredSize(new java.awt.Dimension(91, 25));
        btnlimpiar.setRolloverEnabled(false);
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        btnvolver.setForeground(new java.awt.Color(204, 204, 0));
        btnvolver.setText("VOLVER");
        btnvolver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 2));
        btnvolver.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxadultos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxniños, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechasalida, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(fechallegada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(fechallegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(fechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboxadultos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboxniños, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnlimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione el tipo de habitación deseado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 0))); // NOI18N

        tablatiposhab.setBackground(new java.awt.Color(0, 0, 0));
        tablatiposhab.setForeground(new java.awt.Color(255, 255, 255));
        tablatiposhab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablatiposhab.setGridColor(new java.awt.Color(0, 0, 0));
        tablatiposhab.setShowHorizontalLines(false);
        tablatiposhab.setShowVerticalLines(false);
        tablatiposhab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablatiposhabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablatiposhab);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione la habitacion deseada y luego haga click en siguiente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 0))); // NOI18N

        tablahabs.setBackground(new java.awt.Color(0, 0, 0));
        tablahabs.setForeground(new java.awt.Color(255, 255, 255));
        tablahabs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablahabs.setShowHorizontalLines(false);
        tablahabs.setShowVerticalLines(false);
        tablahabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablahabsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablahabs);

        btnsiguientehab.setForeground(new java.awt.Color(204, 204, 0));
        btnsiguientehab.setText("SIGUIENTE");
        btnsiguientehab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 2));
        btnsiguientehab.setContentAreaFilled(false);
        btnsiguientehab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsiguientehabMouseClicked(evt);
            }
        });
        btnsiguientehab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsiguientehabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnsiguientehab, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsiguientehab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        panelTres.add(jPanel1);
        jPanel1.setBounds(330, 220, 940, 520);

        salir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(204, 204, 0));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/salir.jpg"))); // NOI18N
        salir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        panelTres.add(salir);
        salir.setBounds(1137, 760, 300, 103);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/2.jpg"))); // NOI18N
        fondo.setAlignmentY(0.0F);
        fondo.setMaximumSize(new java.awt.Dimension(2048, 1536));
        panelTres.add(fondo);
        fondo.setBounds(160, 30, 1280, 730);

        Admin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Admin.setForeground(new java.awt.Color(204, 204, 0));
        Admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/adminbt.jpg"))); // NOI18N
        Admin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        Admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminActionPerformed(evt);
            }
        });
        panelTres.add(Admin);
        Admin.setBounds(160, 760, 300, 103);

        reserve.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reserve.setForeground(new java.awt.Color(204, 204, 0));
        reserve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/reserve.jpg"))); // NOI18N
        reserve.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        panelTres.add(reserve);
        reserve.setBounds(660, 760, 300, 103);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTres, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTres, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelTresPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_panelTresPropertyChange
        // TODO add your handling code here:
                Toolkit tk2 =Toolkit.getDefaultToolkit();
        panelTres.setSize((int) tk2.getScreenSize().getWidth(), (int) tk2.getScreenSize().getHeight());
    }//GEN-LAST:event_panelTresPropertyChange

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void AdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminActionPerformed
        // TODO add your handling code here:
        
         panelTres.removeAll();
        panelTres.repaint();
          Admin admin=new Admin();
          admin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_AdminActionPerformed

    private void btnsiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsiguienteActionPerformed
        comprobarFechaLlegada();
    }//GEN-LAST:event_btnsiguienteActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void tablatiposhabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablatiposhabMouseClicked
        //Aquí leo los valores de la fila que el cliente seleccione:
        int seleccion = tablatiposhab.rowAtPoint(evt.getPoint());

        id = Integer.valueOf(String.valueOf(tablatiposhab.getValueAt(seleccion, 0)));
        tipo = String.valueOf(tablatiposhab.getValueAt(seleccion, 1));
        capacidad = Integer.valueOf(String.valueOf(tablatiposhab.getValueAt(seleccion, 2)));
        cant_camas = Integer.valueOf(String.valueOf(tablatiposhab.getValueAt(seleccion, 3)));
        tipo_cama = String.valueOf(tablatiposhab.getValueAt(seleccion, 4));
        precio_noche = Integer.valueOf(String.valueOf(tablatiposhab.getValueAt(seleccion, 5)));

        mostrarHabitaciones();
        //System.out.println(id + " " + tipo + " " + capacidad + " " + cant_camas + " " + precio_noche);
    }//GEN-LAST:event_tablatiposhabMouseClicked

    private void tablahabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablahabsMouseClicked
        btnsiguientehab.setEnabled(true);  //Habilito el botón siguiente para elegir un tipo deseado

        //Aqui debo tomar el id de la habitacion seleccionada por el cliente y luego
        //pasar a la ventana de login/registro.
        int selec = tablahabs.rowAtPoint(evt.getPoint());
        id_hab_elegida = Integer.valueOf(String.valueOf(tablahabs.getValueAt(selec, 0)));
        nro_hab_elegida = Integer.valueOf(String.valueOf(tablahabs.getValueAt(selec, 1)));
    }//GEN-LAST:event_tablahabsMouseClicked

    private void btnsiguientehabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsiguientehabMouseClicked

    }//GEN-LAST:event_btnsiguientehabMouseClicked

    private void btnsiguientehabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsiguientehabActionPerformed
        //Lo que hare ahora es registrar esta reserva, y luego, en la ventana de confirmacion, si el cliente confirma
        //entonces la dejo, si el cliente la descarta, entonces la elimino, y si el cliente la quiere modificar, vuelvo a la
        //ventana de ReservaCliente

        registrarReserva();

        //Aqui debo pasar a la ventana de login/registro.
        panelTres.removeAll();
        panelTres.repaint();
        IngresoLuegoDeReserva log = new IngresoLuegoDeReserva(id_reserva, adultos, niños, nro_hab_elegida, precio_noche);
        panelTres.add(log);
        log.toFront();
        log.setVisible(true);
    }//GEN-LAST:event_btnsiguientehabActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Admin;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnsiguiente;
    private javax.swing.JButton btnsiguientehab;
    private javax.swing.JButton btnvolver;
    private javax.swing.JComboBox<String> cboxadultos;
    private javax.swing.JComboBox<String> cboxniños;
    private com.toedter.calendar.JDateChooser fechallegada;
    private com.toedter.calendar.JDateChooser fechasalida;
    private javax.swing.JPanel fechavalida;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTres;
    private javax.swing.JButton reserve;
    private javax.swing.JButton salir;
    private javax.swing.JTable tablahabs;
    private javax.swing.JTable tablatiposhab;
    // End of variables declaration//GEN-END:variables
}
