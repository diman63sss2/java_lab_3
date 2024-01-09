<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Book Information</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>UUID</th>
                        <th>Content</th>
                        <th>Is Deleted</th>
                        <th>Creation Date</th>
                        <th>Last Modified Date</th>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Role</th>
                    </tr>
                    <xsl:for-each select="ArrayList/item">
                        <tr>
                            <td><a href="/book/books/{id}"><xsl:value-of select="id"/></a></td>
                            <td><xsl:value-of select="uuid"/></td>
                            <td><xsl:value-of select="content"/></td>
                            <td><xsl:value-of select="isDeleted"/></td>
                            <td><xsl:value-of select="creationDate"/></td>
                            <td><xsl:value-of select="lastModifiedDate"/></td>
                            <td><a href="/book/books/author/{user/id}"><font color="blue"><xsl:value-of select="user/id"/></font></a></td>
                            <td><font color="blue"><xsl:value-of select="user/username"/></font></td>
                            <td><font color="blue"><xsl:value-of select="user/firstname"/></font></td>
                            <td><font color="blue"><xsl:value-of select="user/lastname"/></font></td>
                            <td><font color="blue"><xsl:value-of select="user/email"/></font></td>
                            <td><font color="blue"><xsl:value-of select="user/role"/></font></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>