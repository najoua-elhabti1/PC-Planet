<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE xsl:stylesheet [
        <!ELEMENT xsl:stylesheet (xsl:template)*>
        <!ATTLIST xsl:stylesheet
                version CDATA #REQUIRED
                xmlns:xsl CDATA #REQUIRED
                xmlns:fo CDATA #REQUIRED>
        <!ELEMENT xsl:template (fo:root|fo:block)*>
        <!ATTLIST xsl:template
                match CDATA #REQUIRED>
        <!ELEMENT fo:root (fo:layout-master-set|fo:page-sequence)*>
        <!ATTLIST fo:root
                xmlns:fo CDATA #REQUIRED>
        <!ELEMENT fo:layout-master-set (fo:simple-page-master)*>
        <!ELEMENT fo:simple-page-master (fo:region-body)*>
        <!ATTLIST fo:simple-page-master
                master-name CDATA #REQUIRED
                page-height CDATA #REQUIRED
                page-width CDATA #REQUIRED
                margin-top CDATA #REQUIRED
                margin-bottom CDATA #REQUIRED
                margin-left CDATA #REQUIRED
                margin-right CDATA #REQUIRED>
        <!ELEMENT fo:region-body (#PCDATA)>
        <!ATTLIST fo:region-body
                margin-top CDATA #REQUIRED>
        <!ELEMENT fo:page-sequence (fo:flow)*>
        <!ATTLIST fo:page-sequence
                master-reference CDATA #REQUIRED>
        <!ELEMENT fo:flow (fo:block)*>
        <!ATTLIST fo:flow
                flow-name CDATA #REQUIRED>
        <!ELEMENT fo:block (xsl:apply-templates|fo:inline|xsl:value-of|br|fo:block)*>
        <!ATTLIST fo:block
                font-family CDATA #IMPLIED
                font-size CDATA #IMPLIED
                margin-top CDATA #IMPLIED>
        <!ELEMENT xsl:apply-templates (#PCDATA)>
        <!ATTLIST xsl:apply-templates
                select CDATA #REQUIRED>
        <!ELEMENT fo:inline (#PCDATA)>
        <!ATTLIST fo:inline
                font-weight CDATA #REQUIRED>
        <!ELEMENT xsl:value-of (#PCDATA)>
        <!ATTLIST xsl:value-of
                select CDATA #REQUIRED>
        <!ELEMENT br (#PCDATA)>
        ]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <xsl:template match="/">
        <root xmlns="http://www.w3.org/1999/XSL/Format">
            <layout-master-set>
                <region-body margin-top="2cm"/>
            </layout-master-set>
            <page-sequence master-reference="simple">
                <flow flow-name="xsl-region-body">
                    <block font-size="12pt" font-family="serif" margin-top="5mm">
                        <!-- Appliquer le modèle pour chaque produit -->
                        <xsl:apply-templates select="//product"/>
                    </block>
                </flow>
            </page-sequence>
        </root>
    </xsl:template>

    <!-- Modèle pour chaque produit -->
    <xsl:template match="product">
        <fo:block>
            <fo:inline font-weight="bold">Product ID:</fo:inline> <xsl:value-of select="id_product"/><br/>
            <fo:inline font-weight="bold">Product Name:</fo:inline> <xsl:value-of select="productName"/><br/>
            <fo:inline font-weight="bold">Description:</fo:inline> <xsl:value-of select="pr_description"/><br/>
            <fo:inline font-weight="bold">Image:</fo:inline> <xsl:value-of select="image"/><br/>
            <fo:inline font-weight="bold">Price:</fo:inline> <xsl:value-of select="price"/><br/>
            <fo:inline font-weight="bold">Quantity in Stock:</fo:inline> <xsl:value-of select="qte_stock"/><br/>
            <!-- Ajouter d'autres détails du produit si nécessaire -->
            <fo:block/><br/>
        </fo:block>
    </xsl:template>

</xsl:stylesheet>
