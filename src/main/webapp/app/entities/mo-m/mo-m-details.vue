<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="moM">
        <h2 class="jh-entity-heading" data-cy="moMDetailsHeading">
          <span v-text="$t('proAppsApp.moM.detail.title')">MoM</span> {{ moM.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('proAppsApp.moM.title')">Title</span>
          </dt>
          <dd>
            <span>{{ moM.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.meetingDate')">Meeting Date</span>
          </dt>
          <dd>
            <span>{{ moM.meetingDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.minutesWritingDate')">Minutes Writing Date</span>
          </dt>
          <dd>
            <span>{{ moM.minutesWritingDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.meetingObjectif')">Meeting Objectif</span>
          </dt>
          <dd>
            <span v-html="moM.meetingObjectif"></span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.meetingDocReferences')">Meeting Doc References</span>
          </dt>
          <dd>
            <span>{{ moM.meetingDocReferences }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.isMoMActionsClosed')">Is Mo M Actions Closed</span>
          </dt>
          <dd>
            <span>{{ moM.isMoMActionsClosed }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.subjectDecisions')">Subject Decisions</span>
          </dt>
          <dd>
            <span v-html="moM.subjectDecisions"></span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.meetingPhotoCapture')">Meeting Photo Capture</span>
          </dt>
          <dd>
            <div v-if="moM.meetingPhotoCapture">
              <a v-on:click="openFile(moM.meetingPhotoCaptureContentType, moM.meetingPhotoCapture)">
                <img
                  v-bind:src="'data:' + moM.meetingPhotoCaptureContentType + ';base64,' + moM.meetingPhotoCapture"
                  style="max-width: 100%"
                  alt="moM image"
                />
              </a>
              {{ moM.meetingPhotoCaptureContentType }}, {{ byteSize(moM.meetingPhotoCapture) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.dateCreation')">Date Creation</span>
          </dt>
          <dd>
            <span v-if="moM.dateCreation">{{ $d(Date.parse(moM.dateCreation), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.dateModify')">Date Modify</span>
          </dt>
          <dd>
            <span v-if="moM.dateModify">{{ $d(Date.parse(moM.dateModify), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.lastModifyBy')">Last Modify By</span>
          </dt>
          <dd>
            <span>{{ moM.lastModifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.createdBy')">Created By</span>
          </dt>
          <dd>
            <span>{{ moM.createdBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.attendees')">Attendees</span>
          </dt>
          <dd>
            <span>{{ moM.attendees }}</span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.notes')">Notes</span>
          </dt>
          <dd>
            <span v-html="moM.notes"></span>
          </dd>
          <dt>
            <span v-text="$t('proAppsApp.moM.projet')">Projet</span>
          </dt>
          <dd>
            <div v-if="moM.projet">
              <router-link :to="{ name: 'ProjectView', params: { projectId: moM.projet.id } }">{{ moM.projet.code }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="moM.id" :to="{ name: 'MoMEdit', params: { moMId: moM.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./mo-m-details.component.ts"></script>
