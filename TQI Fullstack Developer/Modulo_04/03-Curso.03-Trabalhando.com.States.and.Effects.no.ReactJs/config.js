module.exports = {
  launch_options: { args: ['--no-sandbox'] },
  pdf_options: {
    format: 'A4', // Ver nota abaixo
    printBackground: true,
  },
  stylesheet_encoding: 'utf-8',
  css: `
    @page { size: auto; margin: 0; }
    body { margin: 10mm; }
    .page-break { display: none; }
  `
};
