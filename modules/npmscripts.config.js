/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

const CHECK_AND_FIX_GLOBS = [
	'!settings.json',
	'!tsconfig.json',
	'/{,dxp/}*.{js,ts}',
	'/{,dxp/}{,apps/}**/*.{js,ts,tsx,scss,jsp,jspf}',
];

module.exports = {
	build: {
		bundler: {
			config: {
				imports: {
					'@liferay/address-web': {
						'/': '*',
					},
					'@liferay/amd-loader': {
						'/': '*',
					},
					'@liferay/asset-categories-item-selector-web': {
						'/': '*',
					},
					'@liferay/content-dashboard-web': {
						'/': '*',
					},
					'@liferay/cookies-banner-web': {
						'/': '*',
					},
					'@liferay/document-library-preview-css': {
						'/': '*',
					},
					'@liferay/document-library-web': {
						'/': '*',
					},
					'@liferay/flags-taglib': {
						'/': '*',
					},
					'@liferay/fragment-renderer-collection-filter-impl': {
						'/': '*',
					},
					'@liferay/frontend-data-set-web': {
						'/': '*',
					},
					'@liferay/frontend-icons-web': {
						'/': '*',
					},
					'@liferay/frontend-js-a11y-web': {
						'/': '*',
					},

					'@liferay/frontend-js-dependencies-web': {
						'/': '*',
						'axe-core':"*",
						'clipboard': '*',			
						'cropperjs': '*',
						'cropperjs/dist/cropper.css': '*',
						'dagre': '*',
						'date-fns': '*',
						'dom-align': '*',
						'fuzzy': '*',
						'graphiql':'*',
						'graphiql/graphiql.css': '*',
						'graphql': "*",
						'graphql-hooks': "*",
						'graphql-hooks-memcache': "*",
						'highlight.js': "*",
						'highlight.js/lib/core': "*",
						'highlight.js/lib/languages/java': "*",
						'highlight.js/lib/languages/javascript': "*",
						'highlight.js/lib/languages/plaintext': "*",
						'highlight.js/styles/monokai-sublime.css': "*",
						'image-promise': '*',
						'liferay-ckeditor': '*',
						'lodash.groupby': "*",
						'lodash.isequal': "*",
						'moment': '*',
						'moment/min/moment-with-locales': '*',
						'numeral': "*",
						'object-hash': "*",
						'pkce-challenge': "*",
						'qrcode': '*',
						'qs': '*',
						'react-dropzone': "*",
						"react-flow-renderer": "*",
						'react-helmet': '*',
						'react-router-dom': '*',
						'react-text-mask': '*',
						'react-transition-group': '*',
						'text-mask-addons': '*',
						'text-mask-core': '*',
						'uuid': '*',
					},

					'@liferay/frontend-js-react-web': {
						'/': '*',
						'classnames': '*',
						'formik': '*',
						'prop-types': '*',
						'react': '*',
						'react-dnd': '*',
						'react-dnd-html5-backend': '*',
						'react-dom': '*',
					},
					'@liferay/frontend-js-state-web': {
						'/': '*',
					},
					'@liferay/frontend-js-walkthrough-web': {
						'/': '*',
					},
					'@liferay/frontend-taglib': {
						'/': '*',
					},
					'@liferay/layout-content-page-editor-web': {
						'/': '*',
					},
					'@liferay/map-common': {
						'/': '*',
					},
					'@liferay/map-google-maps': {
						'/': '*',
					},
					'@liferay/map-openstreetmap': {
						'/': '*',
					},
					'@liferay/multi-factor-authentication-fido2-web': {
						'/': '*',
						'base64-js': '*',
					},
					'@liferay/object-js-components-web': {
						'/': '*',
					},
					'@liferay/template-web': {
						'/': '*',
					},
					'asset-taglib': {
						'/': '*',
					},
					'commerce-frontend-js': {
						'/': '*',
					},
					'data-engine-js-components-web': {
						'/': '*',
					},
					'data-engine-taglib': {
						'/': '*',
					},
					'dynamic-data-mapping-form-builder': {
						'/': '*',
					},
					'dynamic-data-mapping-form-field-type': {
						'/': '*',
					},
					'dynamic-data-mapping-form-renderer': {
						'/': '*',
					},
					'dynamic-data-mapping-form-web': {
						'/': '*',
					},
					'frontend-editor-alloyeditor-web': {
						'/': '*',
						'alloyeditor': '*',
					},
					'frontend-editor-ckeditor-web': {
						'/': '*',
						'ckeditor4-react': '*',
					},
					'frontend-js-components-web': {
						'/': '*',
					},
					'frontend-js-metal-web': {
						'incremental-dom': '*',
						'incremental-dom-string': '*',
						'metal': '*',
						'metal-affix': '*',
						'metal-ajax': '*',
						'metal-anim': '*',
						'metal-aop': '*',
						'metal-assertions': '*',
						'metal-clipboard': '*',
						'metal-component': '*',
						'metal-debounce': '*',
						'metal-dom': '*',
						'metal-drag-drop': '*',
						'metal-events': '*',
						'metal-incremental-dom': '*',
						'metal-jsx': '*',
						'metal-key': '*',
						'metal-keyboard-focus': '*',
						'metal-multimap': '*',
						'metal-pagination': '*',
						'metal-path-parser': '*',
						'metal-position': '*',
						'metal-promise': '*',
						'metal-router': '*',
						'metal-scrollspy': '*',
						'metal-soy': '*',
						'metal-soy-bundle': '*',
						'metal-state': '*',
						'metal-storage': '*',
						'metal-structs': '*',
						'metal-throttle': '*',
						'metal-toggler': '*',
						'metal-uri': '*',
						'metal-useragent': '*',
						'metal-web-component': '*',
						'querystring': '*',
						'xss-filters': '*',
					},
					'frontend-js-node-shims': {
						'assert': '*',
						'buffer': '*',
						'domain': '*',
						'domain-browser': '*',
						'events': '*',
						'os': '*',
						'path': '*',
						'process': '*',
						'string_decoder': '*',
						'timers': '*',
						'url': '*',
						'util': '*',
					},
					'frontend-js-recharts': {
						recharts: '*',
					},
					'frontend-js-spa-web': {
						senna: '*',
					},
					'frontend-js-web': {
						'/': '*',
					},
					'frontend-taglib-chart': {
						'billboard.js': '*',
						'clay-charts': '*',
						'd3': '*',
						'd3-array': '*',
						'd3-axis': '*',
						'd3-brush': '*',
						'd3-chord': '*',
						'd3-collection': '*',
						'd3-color': '*',
						'd3-contour': '*',
						'd3-dispatch': '*',
						'd3-drag': '*',
						'd3-dsv': '*',
						'd3-ease': '*',
						'd3-fetch': '*',
						'd3-force': '*',
						'd3-format': '*',
						'd3-geo': '*',
						'd3-hierarchy': '*',
						'd3-interpolate': '*',
						'd3-path': '*',
						'd3-polygon': '*',
						'd3-quadtree': '*',
						'd3-random': '*',
						'd3-scale': '*',
						'd3-scale-chromatic': '*',
						'd3-selection': '*',
						'd3-shape': '*',
						'd3-time': '*',
						'd3-time-format': '*',
						'd3-timer': '*',
						'd3-transition': '*',
						'd3-voronoi': '*',
						'd3-zoom': '*',
					},
					'frontend-taglib-clay': {
						'/': '*',
						'@clayui/alert': '*',
						'@clayui/autocomplete': '*',
						'@clayui/badge': '*',
						'@clayui/breadcrumb': '*',
						'@clayui/button': '*',
						'@clayui/card': '*',
						'@clayui/charts': '*',
						'@clayui/color-picker': '*',
						'@clayui/core': '*',
						'@clayui/css': '*',
						'@clayui/data-provider': '*',
						'@clayui/date-picker': '*',
						'@clayui/drop-down': '*',
						'@clayui/empty-state': '*',
						'@clayui/form': '*',
						'@clayui/icon': '*',
						'@clayui/label': '*',
						'@clayui/layout': '*',
						'@clayui/link': '*',
						'@clayui/list': '*',
						'@clayui/loading-indicator': '*',
						'@clayui/localized-input': '*',
						'@clayui/management-toolbar': '*',
						'@clayui/modal': '*',
						'@clayui/multi-select': '*',
						'@clayui/multi-step-nav': '*',
						'@clayui/nav': '*',
						'@clayui/navigation-bar': '*',
						'@clayui/pagination': '*',
						'@clayui/pagination-bar': '*',
						'@clayui/panel': '*',
						'@clayui/popover': '*',
						'@clayui/progress-bar': '*',
						'@clayui/provider': '*',
						'@clayui/shared': '*',
						'@clayui/slider': '*',
						'@clayui/sticker': '*',
						'@clayui/table': '*',
						'@clayui/tabs': '*',
						'@clayui/time-picker': '*',
						'@clayui/toolbar': '*',
						'@clayui/tooltip': '*',
						'@clayui/upper-toolbar': '*',
						'clay': '*',
						'clay-alert': '*',
						'clay-autocomplete': '*',
						'clay-badge': '*',
						'clay-button': '*',
						'clay-card': '*',
						'clay-card-grid': '*',
						'clay-checkbox': '*',
						'clay-collapse': '*',
						'clay-component': '*',
						'clay-css': '*',
						'clay-data-provider': '*',
						'clay-dataset-display': '*',
						'clay-dropdown': '*',
						'clay-icon': '*',
						'clay-label': '*',
						'clay-link': '*',
						'clay-list': '*',
						'clay-loading-indicator': '*',
						'clay-management-toolbar': '*',
						'clay-modal': '*',
						'clay-multi-select': '*',
						'clay-navigation-bar': '*',
						'clay-pagination': '*',
						'clay-pagination-bar': '*',
						'clay-portal': '*',
						'clay-progress-bar': '*',
						'clay-radio': '*',
						'clay-select': '*',
						'clay-sticker': '*',
						'clay-table': '*',
						'clay-tooltip': '*',
					},
					'item-selector-taglib': {
						'/': '*',
					},
					'social-bookmarks-taglib': {
						'/': '*',
					},
				},
				strictGlobalDependencies: true,
			},
			exclude: {
				'*': ['**/tests/**/*', '**/test/**/*', '**/__tests__/**/*'],
				'@babel/code-frame': true,
				'@babel/generator': true,
				'@babel/helper-annotate-as-pure': true,
				'@babel/helper-builder-binary-assignment-operator-visitor': true,
				'@babel/helper-call-delegate': true,
				'@babel/helper-define-map': true,
				'@babel/helper-explode-assignable-expression': true,
				'@babel/helper-function-name': true,
				'@babel/helper-get-function-arity': true,
				'@babel/helper-hoist-variables': true,
				'@babel/helper-member-expression-to-functions': true,
				'@babel/helper-module-imports': true,
				'@babel/helper-module-transforms': true,
				'@babel/helper-optimise-call-expression': true,
				'@babel/helper-plugin-utils': true,
				'@babel/helper-regex': true,
				'@babel/helper-remap-async-to-generator': true,
				'@babel/helper-replace-supers': true,
				'@babel/helper-simple-access': true,
				'@babel/helper-split-export-declaration': true,
				'@babel/helper-wrap-function': true,
				'@babel/highlight': true,
				'@babel/parser': true,
				'@babel/plugin-proposal-async-generator-functions': true,
				'@babel/plugin-proposal-json-strings': true,
				'@babel/plugin-proposal-object-rest-spread': true,
				'@babel/plugin-proposal-optional-catch-binding': true,
				'@babel/plugin-proposal-unicode-property-regex': true,
				'@babel/plugin-syntax-async-generators': true,
				'@babel/plugin-syntax-json-strings': true,
				'@babel/plugin-syntax-object-rest-spread': true,
				'@babel/plugin-syntax-optional-catch-binding': true,
				'@babel/plugin-transform-arrow-functions': true,
				'@babel/plugin-transform-async-to-generator': true,
				'@babel/plugin-transform-block-scoped-functions': true,
				'@babel/plugin-transform-block-scoping': true,
				'@babel/plugin-transform-classes': true,
				'@babel/plugin-transform-computed-properties': true,
				'@babel/plugin-transform-destructuring': true,
				'@babel/plugin-transform-dotall-regex': true,
				'@babel/plugin-transform-duplicate-keys': true,
				'@babel/plugin-transform-exponentiation-operator': true,
				'@babel/plugin-transform-for-of': true,
				'@babel/plugin-transform-function-name': true,
				'@babel/plugin-transform-literals': true,
				'@babel/plugin-transform-modules-amd': true,
				'@babel/plugin-transform-modules-commonjs': true,
				'@babel/plugin-transform-modules-systemjs': true,
				'@babel/plugin-transform-modules-umd': true,
				'@babel/plugin-transform-named-capturing-groups-regex': true,
				'@babel/plugin-transform-new-target': true,
				'@babel/plugin-transform-object-super': true,
				'@babel/plugin-transform-parameters': true,
				'@babel/plugin-transform-regenerator': true,
				'@babel/plugin-transform-shorthand-properties': true,
				'@babel/plugin-transform-spread': true,
				'@babel/plugin-transform-sticky-regex': true,
				'@babel/plugin-transform-template-literals': true,
				'@babel/plugin-transform-typeof-symbol': true,
				'@babel/plugin-transform-unicode-regex': true,
				'@babel/preset-env': true,
				'@babel/template': true,
				'@babel/traverse': true,
				'@babel/types': true,
				'ansi-styles': true,
				'bootstrap': true,
				'browserslist': true,
				'caniuse-lite': true,
				'chalk': true,
				'commander': true,
				'electron-to-chromium': true,
				'html-webpack-plugin': true,
				'lodash': true,
				'mini-css-extract-plugin': true,
				'regenerate-unicode-properties': true,
				'regenerator-transform': true,
				'regexp-tree': true,
				'rw': true,
				'semver': true,
				'source-map': true,
			},
		},
		dependencies: [
			'asset-taglib',
			'commerce-frontend-taglib',
			'commerce-product-options-web',
			'data-engine-taglib',
			'dynamic-data-mapping-form-builder',
			'dynamic-data-mapping-form-field-type',
			'dynamic-data-mapping-form-renderer',
		],
		imports: {
			'@liferay/frontend-data-set-web': [],
			'@liferay/frontend-js-dependencies-web': [
				'@liferay/js-api',
				'@liferay/js-api/data-set',
				'clipboard',
				'axe-core',
				'cropperjs',
				'cropperjs/dist/cropper.css',
				'dagre',
				'date-fns',
				'dom-align',
				'fuzzy',
				'graphql-hooks-memcache',
				'graphql-hooks',
				'graphql',
				'highlight.js',
				'highlight.js/lib/core',
				'highlight.js/lib/languages/java',
				'highlight.js/lib/languages/javascript',
				'highlight.js/lib/languages/plaintext',
				'highlight.js/styles/monokai-sublime.css',
				'image-promise',
				'liferay-ckeditor',
				'lodash.groupby',
				'lodash.isequal',
				'moment',
				'moment/min/moment-with-locales',
				'numeral',
				'object-hash',
				'pkce-challenge',
				'qrcode',
				'qs',
				'react-dropzone',
				"react-flow-renderer",
				'react-helmet',
				'react-router-dom',
				'react-text-mask',
				'react-transition-group',
				'text-mask-addons',
				'text-mask-core',
				'uuid',
				'graphiql',
				'graphiql/graphiql.css',
			],
			'@liferay/frontend-js-react-web': [
				'classnames',
				'formik',
				'prop-types',
				'react',
				'react-dnd',
				'react-dnd-html5-backend',
				'react-dom',
			],
			'@liferay/frontend-js-state-web': [],
			'frontend-js-components-web': [],
			'frontend-js-metal-web': [
				'incremental-dom',
				'incremental-dom-string',
				'metal',
				'metal-affix',
				'metal-ajax',
				'metal-anim',
				'metal-aop',
				'metal-assertions',
				'metal-clipboard',
				'metal-component',
				'metal-debounce',
				'metal-dom',
				'metal-drag-drop',
				'metal-events',
				'metal-incremental-dom',
				'metal-jsx',
				'metal-key',
				'metal-keyboard-focus',
				'metal-multimap',
				'metal-pagination',
				'metal-path-parser',
				'metal-position',
				'metal-promise',
				'metal-router',
				'metal-scrollspy',
				'metal-soy',
				'metal-soy-bundle',
				'metal-state',
				'metal-storage',
				'metal-structs',
				'metal-throttle',
				'metal-toggler',
				'metal-uri',
				'metal-useragent',
				'metal-web-component',
				'querystring',
				'xss-filters',
			],
			'frontend-js-web': [],
			'frontend-taglib-clay': [
				'@clayui/alert',
				'@clayui/autocomplete',
				'@clayui/badge',
				'@clayui/breadcrumb',
				'@clayui/button',
				'@clayui/card',
				'@clayui/charts',
				'@clayui/color-picker',
				'@clayui/core',
				'@clayui/css',
				'@clayui/data-provider',
				'@clayui/date-picker',
				'@clayui/drop-down',
				'@clayui/empty-state',
				'@clayui/form',
				'@clayui/icon',
				'@clayui/label',
				'@clayui/layout',
				'@clayui/link',
				'@clayui/list',
				'@clayui/loading-indicator',
				'@clayui/localized-input',
				'@clayui/management-toolbar',
				'@clayui/modal',
				'@clayui/multi-select',
				'@clayui/multi-step-nav',
				'@clayui/nav',
				'@clayui/navigation-bar',
				'@clayui/pagination',
				'@clayui/pagination-bar',
				'@clayui/panel',
				'@clayui/popover',
				'@clayui/progress-bar',
				'@clayui/shared',
				'@clayui/slider',
				'@clayui/sticker',
				'@clayui/table',
				'@clayui/tabs',
				'@clayui/time-picker',
				'@clayui/toolbar',
				'@clayui/tooltip',
				'@clayui/upper-toolbar',
				'clay',
				'clay-alert',
				'clay-autocomplete',
				'clay-badge',
				'clay-button',
				'clay-card',
				'clay-card-grid',
				'clay-checkbox',
				'clay-collapse',
				'clay-component',
				'clay-data-provider',
				'clay-dataset-display',
				'clay-dropdown',
				'clay-icon',
				'clay-label',
				'clay-link',
				'clay-list',
				'clay-loading-indicator',
				'clay-management-toolbar',
				'clay-modal',
				'clay-multi-select',
				'clay-navigation-bar',
				'clay-pagination',
				'clay-pagination-bar',
				'clay-portal',
				'clay-progress-bar',
				'clay-radio',
				'clay-select',
				'clay-sticker',
				'clay-table',
				'clay-tooltip',
			],
		},
	},
	check: CHECK_AND_FIX_GLOBS,
	federation: {
		mode: 'disabled',
	},
	fix: CHECK_AND_FIX_GLOBS,
	rules: {
		'allowed-named-scope-exceptions': [

			// A list of placeholder packages registered by Liferay. Anything
			// outside this list should be under the `@liferay/` named scope.

			'account-admin-web',
			'adaptive-media-image-js-web',
			'adaptive-media-web',
			'admin-dxp-theme',
			'analytics-client-js',
			'analytics-reports-web',
			'announcements-web',
			'app-builder-web',
			'app-builder-workflow-web',
			'asset-categories-admin-web',
			'asset-categories-item-selector-web',
			'asset-categories-selector-web',
			'asset-list-web',
			'asset-publisher-web',
			'asset-taglib',
			'asset-tags-admin-web',
			'blogs-web',
			'bookmarks-web',
			'calendar-web',
			'change-tracking-web',
			'classic-dxp-theme',
			'click-to-chat-web',
			'com-liferay-dynamic-data-mapping-test',
			'com-liferay-osb-loop-private',
			'com.liferay.osb.www.resources',
			'commerce-bom-admin-web',
			'commerce-bom-web',
			'commerce-cart-taglib',
			'commerce-dashboard-web',
			'commerce-frontend-impl',
			'commerce-frontend-js',
			'commerce-frontend-taglib',
			'commerce-organization-web',
			'commerce-product-content-web',
			'commerce-product-options-web',
			'commerce-theme-minium-impl',
			'contacts-web',
			'content-dashboard-web',
			'data-engine-js-components-web',
			'data-engine-rest-impl',
			'data-engine-taglib',
			'depot-web',
			'document-library-opener-onedrive-web',
			'document-library-preview-audio',
			'document-library-preview-document',
			'document-library-preview-image',
			'document-library-preview-video',
			'document-library-video',
			'document-library-web',
			'dxp-cloud-emulator',
			'dynamic-data-lists-web',
			'dynamic-data-mapping-data-provider-web',
			'dynamic-data-mapping-form-builder',
			'dynamic-data-mapping-form-field-type',
			'dynamic-data-mapping-form-renderer',
			'dynamic-data-mapping-form-report-web',
			'dynamic-data-mapping-form-web',
			'dynamic-data-mapping-web',
			'expando-web',
			'export-import-changeset-taglib',
			'exportimport-web',
			'flags-taglib',
			'forms-theme-contributor',
			'fragment-display-web',
			'fragment-renderer-collection-filter-impl',
			'fragment-renderer-react-impl',
			'fragment-resources',
			'fragment-web',
			'frontend-compatibility-ie',
			'frontend-editor-alloyeditor-web',
			'frontend-editor-ckeditor-web',
			'frontend-image-editor-capability-brightness',
			'frontend-image-editor-capability-contrast',
			'frontend-image-editor-capability-crop',
			'frontend-image-editor-capability-effects',
			'frontend-image-editor-capability-resize',
			'frontend-image-editor-capability-rotate',
			'frontend-image-editor-capability-saturation',
			'frontend-image-editor-web',
			'frontend-js-alert-support-web',
			'frontend-js-aui-web',
			'frontend-js-clay-sample-web',
			'frontend-js-collapse-support-web',
			'frontend-js-components-web',
			'frontend-js-dropdown-support-web',
			'frontend-js-jquery-web',
			'frontend-js-loader-modules-extender',
			'frontend-js-lodash-web',
			'frontend-js-metal-web',
			'frontend-js-node-shims',
			'frontend-js-react-web',
			'frontend-js-recharts',
			'frontend-js-spa-web',
			'frontend-js-svg4everybody-web',
			'frontend-js-tabs-support-web',
			'frontend-js-tooltip-support-web',
			'frontend-js-web',
			'frontend-taglib',
			'frontend-taglib-chart',
			'frontend-taglib-clay',
			'frontend-taglib-clay-sample-web',
			'frontend-taglib-clay-test-alert-toast-sample-web',
			'frontend-theme-classic-style-guide-sample-web',
			'frontend-theme-font-awesome-web',
			'headless-discovery-web',
			'hello-soy-navigation-web',
			'hello-soy-web',
			'hubspot-js',
			'invitation-invite-members-web',
			'item-selector-taglib',
			'item-selector-upload-web',
			'item-selector-url-web',
			'item-selector-web',
			'japan-theme',
			'journal-article-dynamic-data-mapping-form-field-type',
			'journal-web',
			'knowledge-base-web',
			'layout-admin-web',
			'layout-content-page-editor-web',
			'layout-dynamic-data-mapping-form-field-type',
			'layout-item-selector-web',
			'layout-reports-web',
			'layout-seo-web',
			'layout-set-prototype-web',
			'layout-taglib',
			'layout-template-admin-web',
			'lfris-www-components',
			'liferay-admin-theme',
			'liferay-classic-theme',
			'liferay-fjord-theme',
			'liferay-frontend-theme-styled',
			'liferay-frontend-theme-unstyled',
			'liferay-learn',
			'liferay-node-assert',
			'liferay-node-buffer',
			'liferay-node-console',
			'liferay-node-constants',
			'liferay-node-domain',
			'liferay-node-events',
			'liferay-node-os',
			'liferay-node-path',
			'liferay-node-process',
			'liferay-node-punycode',
			'liferay-node-querystring',
			'liferay-node-setimmediate',
			'liferay-node-string_decoder',
			'liferay-node-timers',
			'liferay-node-tty',
			'liferay-node-url',
			'liferay-node-util',
			'liferay-node-vm',
			'liferay-porygon-theme',
			'liferay-user-dashboard-theme',
			'liferay-user-profile-theme',
			'liferay-watson-web',
			'liferay-westeros-bank-theme',
			'map-google-maps',
			'map-openstreetmap',
			'marketing-fragments',
			'marketplace-store-web',
			'message-boards-web',
			'minium-theme',
			'multi-factor-authentication-fido2-web',
			'multi-factor-authentication-timebased-otp-web',
			'my-configurable-fragment',
			'my-sites-web',
			'my-subscriptions-web',
			'notifications-web',
			'oauth2-provider-web',
			'osb-commerce-portal-instance-admin-theme',
			'osb-commerce-provisioning-theme',
			'osb-commerce-provisioning-theme-impl',
			'osb-commerce-provisioning-web',
			'osb-community-doc-project-heading-web',
			'osb-community-doc-project-index-web',
			'osb-community-doc-project-random-nine-web',
			'osb-community-github-top-contributors-web',
			'osb-community-meetup-web',
			'osb-community-theme',
			'osb-customer-account-entry-details',
			'osb-customer-downloads-display',
			'osb-customer-release-tool',
			'osb-customer-theme',
			'osb-emulator',
			'osb-events-theme',
			'osb-faro-theme',
			'osb-faro-web',
			'osb-knowledge-base-theme',
			'osb-loop-theme',
			'osb-provisioning-theme',
			'osb-provisioning-web',
			'osb-www-foundations-theme-contributor',
			'osb-www-theme',
			'password-policies-admin-web',
			'polls-web',
			'portal-portlet-bridge-soy-impl',
			'portal-reports-engine-console-web',
			'portal-search-admin-web',
			'portal-search-ranking-web',
			'portal-search-synonyms-web',
			'portal-search-web',
			'portal-template-react-renderer-impl',
			'portal-workflow-kaleo-designer-web',
			'portal-workflow-kaleo-forms-web',
			'portal-workflow-metrics-web',
			'portal-workflow-task-web',
			'portal-workflow-web',
			'portlet-configuration-css-web',
			'portlet-configuration-web',
			'poshi-language-support',
			'product-navigation-applications-menu',
			'product-navigation-control-menu',
			'product-navigation-control-menu-web',
			'product-navigation-simulation-device',
			'product-navigation-taglib',
			'questions-web',
			'ratings-taglib',
			'redirect-web',
			'remote-app-client-js',
			'remote-app-support-web',
			'roles-admin-web',
			'segments-experiment-web',
			'segments-simulation-web',
			'segments-web',
			'server-admin-web',
			'sharing-taglib',
			'sharing-web',
			'site-admin-web',
			'site-membership-web',
			'site-navigation-admin-web',
			'site-navigation-item-selector-web',
			'site-navigation-menu-item-layout',
			'site-navigation-menu-web',
			'site-teams-web',
			'social-bookmarks-taglib',
			'speedwell-theme',
			'staging-bar-web',
			'staging-processes-web',
			'staging-taglib',
			'style-book-web',
			'testray-theme',
			'theme-contributor',
			'translation-web',
			'trash-web',
			'user-associated-data-web',
			'user-dashboard-dxp-theme',
			'user-groups-admin-web',
			'user-profile-dxp-theme',
			'users-admin-web',
			'watson-theme',
			'wiki-web',
			'youtube-web',
		],
		'allowed-non-global-dependencies': [
			// Dependencies not expected to be shared

			'@vscode/ripgrep',
			'es-module-shims',
			'liferay-font-awesome',
			'swagger-ui-react',

			// Needs to be removed

			'axios',

			// Causes bugs

			'path-to-regexp',

			// Doesn't support ESM

			'codemirror',
			'leaflet',

			// Node Shims

			'os-browserify',
			'path-browserify',
			'timers-browserify',

			// Analytics Client Bundle

			'browser-tabs-lock',
			'hash.js',
			'core-js',
		],
	},
};
