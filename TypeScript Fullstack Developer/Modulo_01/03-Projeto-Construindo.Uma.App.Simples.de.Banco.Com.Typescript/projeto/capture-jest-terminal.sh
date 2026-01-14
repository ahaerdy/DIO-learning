#!/usr/bin/env bash

# ============================================================
# Captura saída do Jest e gera imagens JPG contínuas
# Dark e Light, com crop final real (SEM FOLGA)
# ============================================================

set -e

BASE_DIR="./capture-jest-terminal"
DPI=200

mkdir -p "$BASE_DIR"

ANSI_FILE="$BASE_DIR/output.ansi"
HTML_RAW="$BASE_DIR/output_raw.html"
HTML_BASE="$BASE_DIR/output_base.html"

HTML_DARK="$BASE_DIR/output_dark.html"
HTML_LIGHT="$BASE_DIR/output_light.html"

PDF_DARK="$BASE_DIR/output_dark.pdf"
PDF_LIGHT="$BASE_DIR/output_light.pdf"

IMG_DARK="$BASE_DIR/terminal_capture-dark.jpg"
IMG_LIGHT="$BASE_DIR/terminal_capture-light.jpg"

# ------------------------------------------------------------
# 1. Executa Jest (cores reais)
# ------------------------------------------------------------
echo "▶ Executando Jest..."
FORCE_COLOR=3 npx jest --verbose --runInBand --coverage > "$ANSI_FILE" 2>&1

# Remove quebra de linha final (ajuda o baseline)
sed -i '${/^$/d;}' "$ANSI_FILE"

# ------------------------------------------------------------
# 2. ANSI → HTML
# ------------------------------------------------------------
echo "▶ Convertendo ANSI para HTML..."
ansifilter --html --encoding=UTF-8 \
  --input "$ANSI_FILE" \
  --output "$HTML_RAW"

cp "$HTML_RAW" "$HTML_BASE"

# ------------------------------------------------------------
# 3. Corrige semanticamente PASS
# ------------------------------------------------------------
sed -E -i \
  's/<span class="ansi-green">PASS<\/span>/<span class="pass-token">PASS<\/span>/g' \
  "$HTML_BASE"

# ------------------------------------------------------------
# 4. HTML DARK
# ------------------------------------------------------------
cat > "$HTML_DARK" <<EOF
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@page { margin: 0; }

body {
  margin: 0;
  background: #0c0c0c;
  color: #e6e6e6;
  font-family: "JetBrains Mono","Fira Code","DejaVu Sans Mono",monospace;
  font-size: 14px;
  line-height: 1.32;
  font-weight: 600;
}

pre {
  margin: 0;
  padding: 0;
  display: inline-block;
}

.pass-token {
  background: #00ff00;
  color: #000;
  font-weight: 800;
  padding: 0 4px;
}
</style>
</head>
<body>
$(cat "$HTML_BASE")
</body>
</html>
EOF

# ------------------------------------------------------------
# 5. HTML LIGHT
# ------------------------------------------------------------
cat > "$HTML_LIGHT" <<EOF
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@page { margin: 0; }

body {
  margin: 0;
  background: #ffffff;
  color: #000000;
  font-family: "JetBrains Mono","Fira Code","DejaVu Sans Mono",monospace;
  font-size: 14px;
  line-height: 1.32;
  font-weight: 600;
}

pre {
  margin: 0;
  padding: 0;
  display: inline-block;
}

.pass-token {
  background: #00cc00;
  color: #000;
  font-weight: 800;
  padding: 0 4px;
}
</style>
</head>
<body>
$(cat "$HTML_BASE")
</body>
</html>
EOF

# ------------------------------------------------------------
# 6. HTML → PDF
# ------------------------------------------------------------
echo "▶ Gerando PDFs..."
weasyprint "$HTML_DARK" "$PDF_DARK"
weasyprint "$HTML_LIGHT" "$PDF_LIGHT"

# ------------------------------------------------------------
# 7. PDF → JPG (arquivo único)
# ------------------------------------------------------------
echo "▶ Convertendo PDFs para JPG..."
pdftoppm -jpeg -r "$DPI" "$PDF_DARK" "$BASE_DIR/tmp-dark"
pdftoppm -jpeg -r "$DPI" "$PDF_LIGHT" "$BASE_DIR/tmp-light"

mv "$BASE_DIR/tmp-dark-1.jpg" "$IMG_DARK"
mv "$BASE_DIR/tmp-light-1.jpg" "$IMG_LIGHT"

# ------------------------------------------------------------
# 8. CROP FINAL REAL (REMOVE FOLGA DEFINITIVAMENTE)
# ------------------------------------------------------------
echo "▶ Cortando folga final..."
convert "$IMG_DARK"  -trim +repage "$IMG_DARK"
convert "$IMG_LIGHT" -trim +repage "$IMG_LIGHT"

# ------------------------------------------------------------
# 9. Limpeza
# ------------------------------------------------------------
rm -f "$BASE_DIR"/tmp-*.jpg

echo
echo "✅ Concluído com sucesso"
echo "📁 Pasta: $BASE_DIR"
echo "📸 Dark : terminal_capture-dark.jpg"
echo "📸 Light: terminal_capture-light.jpg"
