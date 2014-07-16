<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="3.0" xmlns:tns="http://www.example.org/schemaCV"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

    <xsl:import href="templateCompetenceTechnique.xsl"></xsl:import>
    <xsl:import href="templateCompetenceFonctionnelle.xsl"></xsl:import>
    <xsl:import href="templateExperience.xsl"></xsl:import>


    <xsl:output method="html"
                doctype-system="about:legacy-compat"
                encoding="UTF-8"
                indent="yes"/>

    <xsl:template match="/tns:cv">
        <html>
            <head>
                <title>CV de&#160;<xsl:value-of select="identite/prenom"/>&#160;<xsl:value-of select="identite/nom"/></title>

                <link rel="stylesheet" href="css/css.css"/>
            </head>
            <body>
                <div id="container">
                    <div id="header">
                    <div id="logo">
                        <img alt="logo Néo Soft" src="image/Logo Neo-soft.png" width="154" height="88"
                             id="logo_NeoSoft"></img>
                        <img alt="Label Luci Néo-soft" src="image/Label Lucie.png" width="294" height="45"
                             id="logo_labelLucie"></img>
                    </div>
                        <div id="titreFonction">
                            <p class="fonction">
                                <xsl:value-of select="fonction/titre"/>
                            </p>
                            <p class="anneeExperince">
                                <xsl:value-of select="fonction/anneeExperience"/>
                                années d'expérience
                            </p>
                        </div>
                        <div id="identite">
                            <p>habite à
                                <xsl:value-of select="identite/adresse/ville"/>
                            </p>
                        </div>
                    </div>

                    <div id="content">

                        <div id="content-competence">
                            <xsl:call-template name="competenceTechniqueTemplate"></xsl:call-template>

                            <xsl:call-template name="competenceFonctionnelleTemplate"></xsl:call-template>
                        </div>

                        <div id="content-experience">
                            <xsl:call-template name="experienceTemplate"></xsl:call-template>
                        </div>

                    </div>
                    <div id="footer">
                        Copyright © CV AC, 2014
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>