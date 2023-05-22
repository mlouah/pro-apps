<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="company">
        <h2 class="jh-entity-heading" data-cy="companyDetailsHeading">
          <span v-text="$t('proAppsApp.company.detail.title')">Company</span> {{ company.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.company.name')">Name</span>
          </dt>
          <dd>
            <span>{{ company.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.code')">Code</span>
          </dt>
          <dd>
            <span>{{ company.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.companyProfile')">Company Profile</span>
          </dt>
          <dd>
            <span>{{ company.companyProfile }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.url')">Url</span>
          </dt>
          <dd>
            <span>{{ company.url }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.logo')">Logo</span>
          </dt>
          <dd>
            <div v-if="company.logo">
              <a v-on:click="openFile(company.logoContentType, company.logo)">
                <img
                  v-bind:src="'data:' + company.logoContentType + ';base64,' + company.logo"
                  style="max-width: 100%"
                  alt="company image"
                />
              </a>
              {{ company.logoContentType }}, {{ byteSize(company.logo) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="company.dateCreation">{{ $d(Date.parse(company.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="company.dateModify">{{ $d(Date.parse(company.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ company.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ company.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.company.notes')">Notes</span>
          </dt>
          <dd>
            <span>{{ company.notes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="company.id" :to="{ name: 'CompanyEdit', params: { companyId: company.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./company-details.component.ts"></script>
