#!/usr/bin/env bash
set -e

# ============================================================
# Captura a saída do Jest e gera UMA imagem JPG contínua,
# sem bordas, sem margens, fiel ao terminal
# ============================================================

OUTPUT_PREFIX="terminal_capture"
DPI=200

ANSI_FILE="output.ansi"
HTML_RAW="output_raw.html"
HTML_FINAL="output.html"
PDF_FILE="output.pdf"
CSS_FILE="terminal-theme.css"

# ------------------------------------------------------------
# 1. Executa Jest com cores ANSI completas (foreground + background)
# ------------------------------------------------------------
echo "▶ Executando Jest com cores ANSI reais..."
FORCE_COLOR=3 npx jest --verbose --runInBand --coverage > "$ANSI_FILE" 2>&1

# ------------------------------------------------------------
# 2. ANSI → HTML
# ------------------------------------------------------------
echo "▶ Convertendo ANSI para HTML..."
ansifilter \
  --html \
  --input "$ANSI_FILE" \
  --encoding=UTF-8 \
  --output "$HTML_RAW"

# ------------------------------------------------------------
# 3. CSS estilo TERMINAL (sem bordas, sem margens, sem quebra)
# ------------------------------------------------------------
echo "▶ Gerando CSS sem bordas e sem quebra de página..."
cat > "$CSS_FILE" << 'EOF'
@page {
  margin: 0;
  padding: 0;
  size: auto;
}

html, body {
  margin: 0;
  padding: 0;
  background-color: #0c0c0c;
  color: #d4d4d4;
  font-family: "JetBrains Mono", "Fira Code", "DejaVu Sans Mono", monospace;
  font-size: 14px;
  line-height: 1.35;
}

pre {
  margin: 0;
  padding: 0;
  background: transparent !important;
  page-break-inside: avoid;
  break-inside: avoid;
}

span {
  white-space: pre;
}

.ansi-bold {
  font-weight: 600;
}
EOF

# ------------------------------------------------------------
# 4. Injeta o CSS no HTML
# ------------------------------------------------------------
echo "▶ Injetando CSS no HTML..."
sed "s|</head>|<link rel=\"stylesheet\" href=\"$CSS_FILE\"></head>|" \
  "$HTML_RAW" > "$HTML_FINAL"

# ------------------------------------------------------------
# 5. HTML → PDF (sem quebra de página)
# ------------------------------------------------------------
echo "▶ Convertendo HTML para PDF contínuo..."
weasyprint \
  --presentational-hints \
  "$HTML_FINAL" "$PDF_FILE"

# ------------------------------------------------------------
# 6. PDF → JPG (imagem única e longa)
# ------------------------------------------------------------
echo "▶ Convertendo PDF para JPG (imagem única)..."
pdftoppm \
  -jpeg \
  -r "$DPI" \
  "$PDF_FILE" "$OUTPUT_PREFIX"

# ------------------------------------------------------------
# 7. Finalização
# ------------------------------------------------------------
echo
echo "✅ Concluído com sucesso!"
echo "📸 Imagem final (sem bordas, contínua): ${OUTPUT_PREFIX}-1.jpg"
