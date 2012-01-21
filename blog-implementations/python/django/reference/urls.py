from django.conf.urls.defaults import patterns, include, url
from django.conf import settings
from django.contrib import admin

from basic.blog.sitemap import BlogSitemap
#from feeds import BlogPostsFeed

admin.autodiscover()

sitemap_info = { 'sitemaps': {
    'blog':BlogSitemap,
}}

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'reference.views.home', name='home'),
    # url(r'^reference/', include('reference.foo.urls')),

    (r'^comments/', include('django.contrib.comments.urls')),
    # url(r'^blog/rss$',BlogPostsFeed()),

    url(r'^admin/doc/', include('django.contrib.admindocs.urls')),
    url(r'^admin/', include(admin.site.urls)),

    url(r'^',include('basic.blog.urls')),
    url(r'^sitemap.xml$', 'django.contrib.sitemaps.views.sitemap', sitemap_info),
)

handler404 = 'django.views.defaults.page_not_found'
handler500 = 'django.views.defaults.server_error'

