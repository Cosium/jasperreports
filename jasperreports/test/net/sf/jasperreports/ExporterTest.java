package net.sf.jasperreports;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.io.output.NullOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Réda Housni Alaoui
 */
public class ExporterTest {

	private JasperPrint jasperPrint;

	@BeforeEach
	public void beforeEach() throws IOException, JRException {
		JasperReport jasperReport;
		String templatePath = "net/sf/jasperreports/simple-template.jrxml";
		try (InputStream template = getClass().getClassLoader().getResourceAsStream(templatePath)) {
			requireNonNull(template, "Could not find template at '" + templatePath + "'");
			jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(template));
		}
		jasperPrint = JasperFillManager
				.fillReport(
						jasperReport,
						new HashMap<>(),
						new JRBeanCollectionDataSource(
								Collections.singletonList(new SimpleTemplateRow("Chips", "10", "€"))
						)
				);
	}

	@Test
	public void exportToPdf() throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new NullOutputStream());
		exporter.exportReport();
	}

	@Test
	public void exportToExcel() throws JRException {
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new NullOutputStream());
		exporter.exportReport();
	}

}
