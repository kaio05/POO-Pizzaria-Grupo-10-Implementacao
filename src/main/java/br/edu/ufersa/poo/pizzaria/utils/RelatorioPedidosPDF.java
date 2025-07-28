package br.edu.ufersa.poo.pizzaria.utils;

import br.edu.ufersa.poo.pizzaria.model.entities.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class RelatorioPedidosPDF {

    public static void gerarPDF(List<Pedido> pedidos, String caminhoArquivo) throws Exception {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream(caminhoArquivo));
        documento.open();

        // Título
        Font fonteTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph titulo = new Paragraph("Relatório de Pedidos", fonteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        documento.add(titulo);

        // Tabela com 9 colunas agora (nova: valor total)
        PdfPTable tabela = new PdfPTable(9);
        tabela.setWidthPercentage(100);
        tabela.setWidths(new float[]{2f, 2f, 3f, 2f, 2f, 1.5f, 2f, 1.5f, 2f});

        adicionarCabecalho(tabela);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Pedido pedido : pedidos) {
            Cliente cliente = pedido.getCliente();
            Pizza pizza = pedido.getPizza();
            TipoPizza tipo = pizza.getPizza();

            // Concatenar adicionais
            StringBuilder adicionaisStr = new StringBuilder();
            double valorAdicionais = 0.0;
            for (Adicional ad : pedido.getAdicional()) {
                adicionaisStr.append(ad.getNome())
                        .append(" (R$ ")
                        .append(String.format("%.2f", ad.getValor()))
                        .append(")\n");
                valorAdicionais += ad.getValor();
            }

            // Fator por tamanho
            double fator = switch (pedido.getTamanho()) {
                case P -> 1.0;
                case M -> 1.3;
                case G -> 1.5;
            };

            double valorTotal = tipo.getValor() * fator + valorAdicionais;

            // Adiciona células
            tabela.addCell(cliente.getNome());
            tabela.addCell(cliente.getTelefone());
            tabela.addCell(cliente.getEndereco());
            tabela.addCell(tipo.getNome() + " (R$ " + String.format("%.2f", tipo.getValor()) + ")");
            tabela.addCell(adicionaisStr.toString().trim());
            tabela.addCell(pedido.getTamanho().toString());
            tabela.addCell(sdf.format(pedido.getData()));
            tabela.addCell(pedido.getEstado().toString());
            tabela.addCell("R$ " + String.format("%.2f", valorTotal));
        }

        documento.add(tabela);
        documento.close();
        System.out.println("PDF gerado em: " + caminhoArquivo);
    }

    private static void adicionarCabecalho(PdfPTable tabela) {
        Font fonteCabecalho = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        String[] colunas = {
                "Cliente", "Telefone", "Endereço", "Pizza", "Adicionais", "Tamanho", "Data", "Estado", "Valor Total (R$)"
        };

        for (String coluna : colunas) {
            PdfPCell cell = new PdfPCell(new Phrase(coluna, fonteCabecalho));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabela.addCell(cell);
        }
    }
}
