<!--  -->
<template>
    <div class="input-auto-complete">
        <el-input
            v-model="input"
            :placeholder="placeholder"
            style="width: 250px"
            clearable
            @clear="init"
            @keyup.enter.native="init"
        >
            <i
                slot="prefix"
                class="el-input__icon el-icon-search"
                style="cursor: pointer"
                @click="init"
            />
        </el-input>
    </div>
</template>

<script lang="ts">
export default {
    name: 'InputAutoComplete',
}
</script>

<script lang="ts" setup>
import { ref } from 'vue'

const emits = defineEmits(['init'])
const props = defineProps({
    data: { type: Array<any>, default: [] },
    placeholder: {
        type: String,
        default: '',
    },
    ObKey: {
        type: String,
        default: 'name',
    },
})

let input = ref<String>('')

const init = () => {
    emits('init', input.value)
}
</script>
<style scoped>
.input-auto-complete {
    display: inline-block;
}
</style>
