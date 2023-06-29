/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package SchoolShorts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.BooleanUtils;

/**
 *
 * @author hamma
 */
@WebServlet(name = "ServletPDFCountingTable", urlPatterns = {"/ServletPDFCountingTable"})
public class ServletPDFCountingTable extends HttpServlet {

    private static final int tableColumns = 10;
    private static final float borderWidth = 3.0f;    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int countBy = NumberUtils.toInt(request.getParameter("countBy"), 2);
        int countUptil = NumberUtils.toInt(request.getParameter("countUptil"),100);
        boolean showBlanks = true;
        if (request.getParameter("showBlanks")!=null) {
            showBlanks = BooleanUtils.toBoolean(request.getParameter("showBlanks"), "true", "false");
        }
        String domain = request.getServerName();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            generatePDF(baos, domain, false, countBy, countUptil, showBlanks);
        } catch (DocumentException | NumberFormatException | IllegalStateException | IndexOutOfBoundsException | FileNotFoundException ex) {
            throw new IOException(ex.getMessage());
        }
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   
    private void generatePDF(ByteArrayOutputStream baos, String domain, boolean borders, int countBy, int countUptil, boolean showBlanks)
            throws DocumentException, NumberFormatException,
            IllegalStateException, IndexOutOfBoundsException, FileNotFoundException {
        // step 1
        Document document = new Document(PageSize.LETTER);
        // step 2
        PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("SchoolShorts - Counting Table - " + domain));
        document.add(new Paragraph(new Date().toString()));
        document.add(Chunk.NEWLINE);

        PdfPTable table = null;
        PdfPCell cell = null;
        int columns = 0;
        Font fontNormal = new Font(FontFamily.COURIER, 20, Font.NORMAL);
        Font fontBold = new Font(FontFamily.COURIER, 20, Font.BOLD);
        
        table = new PdfPTable(tableColumns);
        table.setWidthPercentage(100);
        table.setSpacingBefore(0);

        PdfPTableEvent tableEvent = new HelperForTable();
        table.setTableEvent(tableEvent);
        boolean markedCell = false;
        
        for (int i = 1; i <= countUptil; i++) { 
            cell = new PdfPCell(new Paragraph(((i%countBy)==0 & showBlanks)?" ":Integer.toString(i),((i%countBy)==0)?fontBold:fontNormal));
            cell.setBorder(borders ? PdfPCell.NO_BORDER : PdfPCell.BOX);
            if((i%countBy)==0) {
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            }
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(40f);
            table.addCell(cell);
        }
        document.add(table);
        // step 4
        document.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Counting Table generator servlet. Generates PDF stream output.";
    }// </editor-fold>

    //Inner class
    class HelperForTable implements PdfPTableEvent {

        @Override
        public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
            int columns;
            Rectangle rect;
            int footer = widths.length - table.getFooterRows();
            int header = table.getHeaderRows() - table.getFooterRows() + 1;
            for (int row = header; row < footer; row += 2) {
                columns = widths[row].length - 1;
                rect = new Rectangle(widths[row][0], heights[row],
                        widths[row][columns], heights[row + 1]);
                //rect.setBackgroundColor(BaseColor.YELLOW);
                rect.setBorder(Rectangle.NO_BORDER);
                canvases[PdfPTable.BASECANVAS].rectangle(rect);
            }
        }
    }
   
}
