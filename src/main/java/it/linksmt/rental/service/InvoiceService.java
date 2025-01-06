package it.linksmt.rental.service;

import it.linksmt.rental.entity.ReservationEntity;
import it.linksmt.rental.entity.UserEntity;
import it.linksmt.rental.repository.ReservationRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class InvoiceService {
    private ReservationRepository reservationRepository;
    public InvoiceService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    private static final String invoice_folder = "invoices/";

            public String generateInvoice(Long reservationId) {
               Optional<ReservationEntity> reservation= reservationRepository.findById(reservationId);
                if(reservation.isEmpty()) {
                    throw new RuntimeException("reservation not found");
                }
                try{
                    //check if folder exists
                    Path path = Paths.get(invoice_folder);
                    if(!Files.exists(path)){
                        Files.createDirectories(path);
                    }
                    String filename = invoice_folder + "invoice_"+reservationId + ".pdf";
                   try (PDDocument document = new PDDocument()){
                       PDPage page = new PDPage();
                       document.addPage(page);
                       try (PDPageContentStream contentStream = new PDPageContentStream(document, page)){
                           PDType1Font font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
                           contentStream.setFont(font, 18);

                           contentStream.beginText();
                           contentStream.setLeading(14.5f);
                           contentStream.newLineAtOffset(50, 750);
                           contentStream.showText("Invoice");
                           contentStream.newLine();
                           UserEntity user = reservation.get().getUser();
                           PDType1Font font2 = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
                           contentStream.setFont(font2, 12);
                           contentStream.newLine();
                           contentStream.showText("Reservation ID: " + reservationId);
                           contentStream.newLine();
                           contentStream.showText("Customer Name: " + user.getName());
                           contentStream.newLine();
                           contentStream.showText("Email: " + user.getEmail());
                           contentStream.newLine();

                           contentStream.showText("Vehicle Details: " + reservation.get().getVehicle());
                           contentStream.newLine();
                           contentStream.showText("Reservation Date: " + reservation.get().getCreatedAt());
                           contentStream.newLine();
                           contentStream.showText("Total Amount: $" + reservation.get().getTotalPrice());
                           contentStream.endText();
                       }
                       document.save(filename);
                       }
return filename;
                } catch (IOException e) {
                    throw new RuntimeException("Error generating invoice PDF", e);
                }
            }
}