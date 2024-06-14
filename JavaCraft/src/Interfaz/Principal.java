package Interfaz;

import java.io.File;
import javax.swing.WindowConstants;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Clases.Documento;
import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javacraft.AppState;
import simbolo.Arbol;
import simbolo.tablaSimbolos;

public class Principal extends javax.swing.JFrame {

    static private String rutaGlobal;

    public Principal() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("DATAFORGE");
        llenarPestañas();

        tablaPestañas.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    int filaSeleccionada = tablaPestañas.getSelectedRow();

                    if (filaSeleccionada >= 0) {
                        actualizarContenido();
                        String contenido = AppState.pestañas.get(filaSeleccionada).getContenido();
                        CodigoTextArea.setText(contenido);

                        rutaGlobal = AppState.pestañas.get(filaSeleccionada).getRuta();
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        CodigoTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPestañas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        ConsolaTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        archivo = new javax.swing.JMenu();
        nuevo = new javax.swing.JMenuItem();
        abrir = new javax.swing.JMenuItem();
        guardar = new javax.swing.JMenuItem();
        cerrar = new javax.swing.JMenuItem();
        ejecutarM = new javax.swing.JMenu();
        ejecutar = new javax.swing.JMenuItem();
        reportes = new javax.swing.JMenu();
        reporte_errores = new javax.swing.JMenuItem();
        ast = new javax.swing.JMenuItem();
        reporte_tabla_de_simbolos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CodigoTextArea.setColumns(20);
        CodigoTextArea.setRows(5);
        jScrollPane1.setViewportView(CodigoTextArea);

        tablaPestañas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pestañas"
            }
        ));
        jScrollPane2.setViewportView(tablaPestañas);

        ConsolaTextArea.setBackground(new java.awt.Color(153, 153, 153));
        ConsolaTextArea.setColumns(20);
        ConsolaTextArea.setForeground(new java.awt.Color(255, 255, 255));
        ConsolaTextArea.setRows(5);
        jScrollPane3.setViewportView(ConsolaTextArea);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSOLA");

        archivo.setText("Archivo");

        nuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        archivo.add(nuevo);

        abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        abrir.setText("Abrir");
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        archivo.add(abrir);

        guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        archivo.add(guardar);

        cerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        cerrar.setText("Cerrar");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        archivo.add(cerrar);

        menu.add(archivo);

        ejecutarM.setText("Ejecutar");

        ejecutar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ejecutar.setText("Ejecutar");
        ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarActionPerformed(evt);
            }
        });
        ejecutarM.add(ejecutar);

        menu.add(ejecutarM);

        reportes.setText("Reportes");

        reporte_errores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        reporte_errores.setText("Errores");
        reportes.add(reporte_errores);

        ast.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ast.setText("AST");
        ast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                astActionPerformed(evt);
            }
        });
        reportes.add(ast);

        reporte_tabla_de_simbolos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        reporte_tabla_de_simbolos.setText("Tabla de Símbolos");
        reportes.add(reporte_tabla_de_simbolos);

        menu.add(reportes);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void llenarPestañas() {

        DefaultTableModel modeloDafault = new DefaultTableModel(new String[]{"Pestañas"}, AppState.pestañas.size());
        tablaPestañas.setModel(modeloDafault);

        TableModel modeloDatos = tablaPestañas.getModel();
        for (int i = 0; i < AppState.pestañas.size(); i++) {
            modeloDatos.setValueAt(AppState.pestañas.get(i).getNombre(), i, 0);
        }

    }

    private void actualizarContenido() {
        //Antes de cualquier cambio actualizaremos el contenido para evitar guardar el documento en la memoria estatica y asi 
        //darle a el usuario la decision si quiere guardar los cambios...
        for (int i = 0; i < AppState.pestañas.size(); i++) {
            Documento obj = AppState.pestañas.get(i);
            if (obj.getRuta().equals(rutaGlobal)) {
                String texto = CodigoTextArea.getText();
                obj.setContenido(texto);
            }
        }
    }


    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
        FileDialog fileDialog = new FileDialog((Frame) null);
        fileDialog.setFile("*.jc");
        fileDialog.setVisible(true);

        String nombreArchivo = fileDialog.getFile();
        String directorio = fileDialog.getDirectory();
        String rutaCompleta = directorio + nombreArchivo;

        if (nombreArchivo != null) {

            boolean encontrada = false;

            File archivo = new File(rutaCompleta);
            if (archivo.exists()) {
                for (int i = 0; i < AppState.pestañas.size(); i++) {
                    String rutaExistente = AppState.pestañas.get(i).getRuta();
                    if (rutaExistente.equals(rutaCompleta)) {
                        encontrada = true;
                        break;
                    }
                }
                

                if (encontrada == false) {
                    try {

                        actualizarContenido();

                        rutaGlobal = rutaCompleta;

                        BufferedReader br = new BufferedReader(new FileReader(rutaCompleta));
                        StringBuilder contenido = new StringBuilder();

                        String linea;

                        while ((linea = br.readLine()) != null) {
                            contenido.append(linea).append("\n");
                        }

                        br.close();

                        CodigoTextArea.setText(contenido.toString());

                        Documento documento = new Documento(nombreArchivo, rutaCompleta, contenido.toString());
                        AppState.pestañas.add(documento);

                        llenarPestañas();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo ya se encuentra abierto", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La ruta especificada no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_abrirActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        for (int i = 0; i < AppState.pestañas.size(); i++) {
            Documento obj = AppState.pestañas.get(i);
            if (obj.getRuta().equals(rutaGlobal)) {
                AppState.pestañas.remove(i);
                CodigoTextArea.setText("");
                llenarPestañas();
                rutaGlobal = "";
            }
        }
    }//GEN-LAST:event_cerrarActionPerformed


    private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarActionPerformed
        
        try {
            String texto = CodigoTextArea.getText();
            scanner s = new scanner(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            for (var a : ast.getInstrucciones()) {
                var res = a.interpretar(ast, tabla);
            }
            String c = ast.getConsola();
            c = c.replace('\"','"');
            ConsolaTextArea.setText(c);
        } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
    }//GEN-LAST:event_ejecutarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed

        try {
            String texto = CodigoTextArea.getText();
            if (rutaGlobal != null && !rutaGlobal.isEmpty()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaGlobal));
                writer.write(texto);
                writer.close();
                JOptionPane.showMessageDialog(null, "Documento Actualizado!!!", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Para actualizar el archivo primero debe de existir", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            // Manejar excepciones de entrada/salida (IOException)
            ex.printStackTrace();
        }

    }//GEN-LAST:event_guardarActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed

        FileDialog fileDialog = new FileDialog((Frame) null, "Nuevo", FileDialog.SAVE);
        fileDialog.setFile("*.jc");
        fileDialog.setVisible(true);

        String nombreArchivo = fileDialog.getFile();
        String directorio = fileDialog.getDirectory();

        if (nombreArchivo != null) {
            String rutaCompleta = directorio + nombreArchivo;
            boolean encontrada = false;

            for (int i = 0; i < AppState.pestañas.size(); i++) {
                String rutaExistente = AppState.pestañas.get(i).getRuta();
                if (rutaExistente.equals(rutaCompleta)) {
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                try {
                    actualizarContenido();
                    rutaGlobal = rutaCompleta;
                    File nuevoArchivo = new File(rutaCompleta);

                    if (nuevoArchivo.createNewFile()) {
                        CodigoTextArea.setText("");
                        Documento documento = new Documento(nombreArchivo, rutaCompleta, "");
                        AppState.pestañas.add(documento);
                        llenarPestañas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe en el directorio", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_nuevoActionPerformed

    private void astActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_astActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_astActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea CodigoTextArea;
    private javax.swing.JTextArea ConsolaTextArea;
    private javax.swing.JMenuItem abrir;
    private javax.swing.JMenu archivo;
    private javax.swing.JMenuItem ast;
    private javax.swing.JMenuItem cerrar;
    private javax.swing.JMenuItem ejecutar;
    private javax.swing.JMenu ejecutarM;
    private javax.swing.JMenuItem guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem nuevo;
    private javax.swing.JMenuItem reporte_errores;
    private javax.swing.JMenuItem reporte_tabla_de_simbolos;
    private javax.swing.JMenu reportes;
    private javax.swing.JTable tablaPestañas;
    // End of variables declaration//GEN-END:variables
}
